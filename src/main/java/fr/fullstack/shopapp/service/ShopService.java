package fr.fullstack.shopapp.service;

import fr.fullstack.shopapp.model.Product;
import fr.fullstack.shopapp.model.Shop;
import fr.fullstack.shopapp.repository.ShopRepository;
import org.hibernate.Session;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Shop createShop(Shop shop) throws Exception {
        try {
            Shop newShop = shopRepository.save(shop);
            // Refresh the entity after the save. Otherwise, @Formula does not work.
            em.flush();
            em.refresh(newShop);
            return newShop;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void deleteShopById(long id) throws Exception {
        try {
            Shop shop = getShop(id);
            // delete nested relations with products
            deleteNestedRelations(shop);
            shopRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Shop getShopById(long id) throws Exception {
        try {
            return getShop(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Shop> getShopList(
            Optional<String> sortBy,
            Optional<Boolean> inVacations,
            Optional<String> createdBefore,
            Optional<String> createdAfter,
            Optional<String> name,
            Pageable pageable
    ) {
        // SORT
        if (sortBy.isPresent()) {
            switch (sortBy.get()) {
                case "name":
                    return shopRepository.findByOrderByNameAsc(pageable);
                case "createdAt":
                    return shopRepository.findByOrderByCreatedAtAsc(pageable);
                default:
                    return shopRepository.findByOrderByNbProductsAsc(pageable);
            }
        }

        // FILTERS
        Page<Shop> shopList = getShopListWithFilter(sortBy,inVacations, createdBefore, createdAfter, name, pageable);
        if (shopList != null) {
            return shopList;
        }

        // NONE
        return shopRepository.findByOrderByIdAsc(pageable);
    }

    @Transactional
    public Shop updateShop(Shop shop) throws Exception {
        try {
            getShop(shop.getId());
            return this.createShop(shop);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void deleteNestedRelations(Shop shop) {
        List<Product> products = shop.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.setShop(null);
            em.merge(product);
            em.flush();
        }
    }

    private Shop getShop(Long id) throws Exception {
        Optional<Shop> shop = shopRepository.findById(id);
        if (!shop.isPresent()) {
            throw new Exception("Shop with id " + id + " not found");
        }
        return shop.get();
    }

    public Page<Shop> getShopListWithFilter(
            Optional<String> sortBy,
            Optional<Boolean> inVacations,
            Optional<String> createdAfter,
            Optional<String> createdBefore,
            Optional<String> name,
            Pageable pageable
    ) {
        SearchSession searchSession = Search.session(em);
        MassIndexer indexer = searchSession.massIndexer( Shop.class );

        List<Shop> result = new ArrayList<>();
        try{
            indexer.startAndWait();

            if(name.isPresent()){
                result = searchSession.search(Shop.class)
                         .where(f -> f.match()
                                 .field( "name" )
                                 .matching( name.get() )).fetchHits(pageable.getPageNumber(), pageable.getPageSize());
            }
            else{
                result = shopRepository.findAll(pageable).toList();
            }

            if(inVacations.isPresent())
                result = result.stream().filter(x -> x.isInVacations() == inVacations.get()).toList();
            if(createdAfter.isPresent())
                result = result.stream().filter(x -> x.getCreatedAt().isAfter(LocalDate.parse(createdAfter.get()))).collect(Collectors.toList());
            if(createdBefore.isPresent())
                result = result.stream().filter(x -> x.getCreatedAt().isBefore(LocalDate.parse(createdBefore.get()))).collect(Collectors.toList());


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(sortBy.isPresent()){
            result = new ArrayList<>(result);
            if(sortBy.get().equals("name")) {
                result.sort(Comparator.comparing(Shop::getName));
            }
            else if(sortBy.get().equals("createdAt")) {
                result.sort(Comparator.comparing(Shop::getCreatedAt));
            }
            else {
                result.sort(Comparator.comparing(Shop::getNbProducts));
            }
        }

        return new PageImpl<>(result,pageable, shopRepository.count());
    }
}
