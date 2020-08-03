package com.iammybest.hbase.springboot.hbase.handler.impl;

import com.iammybest.hbase.springboot.hbase.dao.IVehStatusHbaseDao;
import com.iammybest.hbase.springboot.hbase.domain.VehicleStatusInfoTable;
import com.iammybest.hbase.springboot.hbase.domain.VehicleStatusValue;
import com.iammybest.hbase.springboot.hbase.handler.IVehHBaseHandler;
import com.iammybest.hbase.springboot.hbase.pojo.HistoryVo;
import com.timanetworks.tpc.dao.base.exception.HBaseException;
import com.timanetworks.tsf.core.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @DESCRIBE:
 * @TIME: 2020/4/29 10:33
 * @AUTHOR: qinghai.deng
 **/
@Service
@Slf4j
public class VehHBaseHandler  implements IVehHBaseHandler {

    @Resource
    private IVehStatusHbaseDao hbaseDao;
    public void get() {
        try {
            VehicleStatusInfoTable statusInfoTable = hbaseDao.findVehicleStatusDataByReportTime("LFV1A23C6K3400108", "audiNational", "vehicle.motor.drive.event", Long.MAX_VALUE - 1588069225611L);
            log.info(JSONUtil.writeValueAsString(statusInfoTable));
            String firstGvid = null;
            List<HistoryVo> historyVos = null;
            List<List<HistoryVo>> historyVoList =  new ArrayList<List<HistoryVo>>();
            for(VehicleStatusValue item:statusInfoTable.getItems()){
                if(firstGvid==null||item.equals(item.getGvid())){
                    historyVos = new ArrayList<HistoryVo>();
                    firstGvid = item.getGvid();
                    historyVoList.add(historyVos);
                    log.info("新的一组数据");
                }
                HistoryVo vo = new HistoryVo();
                vo.setGvid(item.getGvid());
                vo.setUnit(item.getUint());
                vo.setValue(item.getValue());
                vo.setName(item.getDisplayNameZh());
                historyVos.add(vo);
                log.info(JSONUtil.writeValueAsString(vo));
            }
        } catch (HBaseException e) {
            e.printStackTrace();
        }
    }
}
