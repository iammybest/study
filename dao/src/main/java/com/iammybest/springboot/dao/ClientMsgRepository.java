package com.iammybest.springboot.dao;

import com.iammybest.springboot.domain.ClientMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @DESCRIBE:
 * @TIME: 2019/11/26 9:32
 * @AUTHOR: qinghai.deng
 **/
public interface ClientMsgRepository extends JpaRepository<ClientMsg, String>, JpaSpecificationExecutor<ClientMsg> {
}
