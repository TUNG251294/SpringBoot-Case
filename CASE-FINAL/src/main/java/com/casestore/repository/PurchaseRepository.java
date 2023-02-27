//package com.casestore.repository;
//
//import com.casestore.model.PurchaseModelRequest;
//import com.casestore.model.entity.Purchase;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//@Repository
//public abstract class PurchaseRepository implements IPurchaseRepository {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public PurchaseModelRequest send(PurchaseModelRequest purchaseModel) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            Purchase origin = new Purchase();
//            origin.setId(purchaseModel.getId());
//            origin.setProductItemList(purchaseModel.getProductItemList());
//            session.saveOrUpdate(origin);
//            transaction.commit();
//            return purchaseModel;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return null;
//    }
//}
