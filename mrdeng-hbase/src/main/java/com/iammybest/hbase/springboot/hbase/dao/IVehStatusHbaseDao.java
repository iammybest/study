package com.iammybest.hbase.springboot.hbase.dao;

import com.iammybest.hbase.springboot.hbase.domain.VehicleStatusInfoTable;
import com.timanetworks.tpc.dao.base.exception.HBaseException;

import java.util.List;


public interface IVehStatusHbaseDao {

    public VehicleStatusInfoTable saveVehicleStatusDataTable(VehicleStatusInfoTable entity) throws HBaseException;

    VehicleStatusInfoTable findLatestVehicleStatusData(String vin, String tenantId, String eventId) throws HBaseException;

    VehicleStatusInfoTable findVehicleStatusDataByReportTime(String vin, String tenantId, String eventId, long reportTime) throws HBaseException;

    List<VehicleStatusInfoTable> findMutiVehicleStatusData(String vin, String tenantId, String eventId, String startDate,
                                                           String endDate, String pageSize, String pageToken) throws HBaseException;

    List<VehicleStatusInfoTable> findMutiVehicleStatusData1(String vin, String tenantId, String eventId, String startDate,
                                                            String endDate) throws HBaseException;

    List<VehicleStatusInfoTable> queryUploadCounts(Long startDate, Long endDate, String tenantId) throws HBaseException;

    List<VehicleStatusInfoTable> loadVehStatusInfoByTime(String vin, String tenantId, String eventId, Long startDate, Long endDate) throws HBaseException;
}