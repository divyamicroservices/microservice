package com.ibm.ordermicroservice.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;





public interface OrderJPARepository extends JpaRepository<UserOrders, Integer> {

	
	/**
     * Find Orders based on loginName.
     */
    public List<UserOrders> findByusernameLike(String username);
    
     
    @Query(value="Select max(orderno) FROM userorders ", nativeQuery = true)
    int getMaxOrderNo();
}
