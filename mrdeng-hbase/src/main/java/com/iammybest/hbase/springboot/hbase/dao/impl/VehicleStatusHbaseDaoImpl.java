package com.iammybest.hbase.springboot.hbase.dao.impl;


/**
 * @Description:
 * @Author: dejun.yang
 * @Date: 2015/10/20
 * @Time: 16:20
 */

import com.iammybest.hbase.springboot.hbase.dao.IVehStatusHbaseDao;
import com.iammybest.hbase.springboot.hbase.domain.VehicleStatusInfoTable;
import com.timanetworks.tpc.dao.base.environment.Context;
import com.timanetworks.tpc.dao.base.exception.HBaseException;
import com.timanetworks.tpc.dao.base.persistence.EntityManager;
import com.timanetworks.tpc.dao.base.query.Criteria;
import com.timanetworks.tpc.dao.base.query.Query;
import com.timanetworks.tpc.dao.base.util.EntityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Base64;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleStatusHbaseDaoImpl extends EntityManager implements IVehStatusHbaseDao {

    private static final String[] allCf = {VehicleStatusInfoTable.ColumnFamily.DATA, VehicleStatusInfoTable.ColumnFamily.STATUS_ITEM};
    private static final String[] dataCf = {VehicleStatusInfoTable.ColumnFamily.DATA};

    public VehicleStatusHbaseDaoImpl() {
        super(new Context());
    }

    public VehicleStatusInfoTable saveVehicleStatusDataTable(VehicleStatusInfoTable entity) throws HBaseException {
        if (entity.getItems() == null || entity.getItems().size() == 0) {
            return super.save(entity, dataCf);
        } else {
            return super.save(entity, allCf);
        }
    }

    public VehicleStatusInfoTable findLatestVehicleStatusData(String vin, String tenantId, String eventId) throws HBaseException {
        return findLatestVehicleStatusData(vin, tenantId, eventId, allCf);
    }

    public VehicleStatusInfoTable findLatestVehicleStatusData(String vin, String tenantId, String eventId, String[] cf) throws HBaseException {
        byte[] startKey = VehicleStatusInfoTable.partialKey(vin, tenantId, eventId);
        byte[] stopKey = EntityUtils.getStopKey(startKey);

        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, cf);

        List<VehicleStatusInfoTable> result = query.start(startKey).limit(1).stop(stopKey).execute();
        if (result == null || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public VehicleStatusInfoTable findVehicleStatusDataByReportTime(String vin, String tenantId, String eventId, long reportTime) throws HBaseException {
        byte[] startKey = VehicleStatusInfoTable.partialKey(vin, tenantId, eventId, reportTime);
        byte[] stopKey = EntityUtils.getStopKey(startKey);

        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, allCf);

        List<VehicleStatusInfoTable> result = query.start(startKey).limit(1).stop(stopKey).execute();
        if (result == null || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public List<VehicleStatusInfoTable> findMutiVehicleStatusData(String vin, String tenantId, String eventId, String startDate,
                                                                  String endDate, String pageSize, String pageToken) throws HBaseException {
        return findMutiVehicleStatusData(vin, tenantId, eventId, startDate, endDate, pageSize, pageToken, allCf);
    }

    public List<VehicleStatusInfoTable> findMutiVehicleStatusData1(String vin, String tenantId, String eventId, String startDate, String endDate) throws HBaseException {
        byte[] startKey = VehicleStatusInfoTable.partialKey(vin, tenantId, eventId);
        byte[] stopKey = EntityUtils.getStopKey(VehicleStatusInfoTable.partialKey(vin, tenantId, eventId));

        List<Criteria.Expression> otherExpressions = new ArrayList<Criteria.Expression>();
        if (startDate != null && !"".equals(startDate)) {
            Criteria.Expression expression = Criteria.lse("data.reportTime", Long.MAX_VALUE - Long.valueOf(startDate));
            otherExpressions.add(expression);
        }
        if (endDate != null && !"".equals(endDate)) {
            Criteria.Expression expression = Criteria.gte("data.reportTime", Long.MAX_VALUE - Long.valueOf(endDate));
            otherExpressions.add(expression);
        }
        Criteria.Expression otherCondition = Criteria.and(otherExpressions);
        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, dataCf);
        query.getOptions().setPageSize(-1);

        List<VehicleStatusInfoTable> result = query.start(startKey).stop(stopKey).where(otherCondition).execute();

        return result;
    }

    public List<VehicleStatusInfoTable> findMutiVehicleStatusData(String vin, String tenantId, String eventId, String startDate,
                                                                  String endDate, String pageSize, String pageToken, String[] cf) throws HBaseException {
        byte[] startKey = null;
        byte[] stopKey = null;
        if (pageToken != null && !"".equals(pageToken)) {
            pageToken = pageToken.replace("\\n", "");
            startKey = Base64.decode(pageToken);

        } else {
            startKey = VehicleStatusInfoTable.partialKey(vin, tenantId, eventId);
        }
        stopKey = EntityUtils.getStopKey(VehicleStatusInfoTable.partialKey(vin, tenantId, eventId));

        List<Criteria.Expression> otherExpressions = new ArrayList<Criteria.Expression>();
        if (startDate != null && !"".equals(startDate)) {
            Criteria.Expression expression = Criteria.lse("data.reportTime", Long.MAX_VALUE - Long.valueOf(startDate));
            otherExpressions.add(expression);
        }
        if (endDate != null && !"".equals(endDate)) {
            Criteria.Expression expression = Criteria.gte("data.reportTime", Long.MAX_VALUE - Long.valueOf(endDate));
            otherExpressions.add(expression);
        }
        Criteria.Expression otherCondition = Criteria.and(otherExpressions);
        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, cf);

        if (pageSize != null && !"".equals(pageSize)) {
            query.limit(Integer.valueOf(pageSize) + 1);
        } else {
            query.getOptions().setPageSize(-1);
        }

        List<VehicleStatusInfoTable> result = query.start(startKey).stop(stopKey).where(otherCondition).execute();

        return result;
    }

    public List<VehicleStatusInfoTable> queryUploadCounts(Long startDate, Long endDate, String tenantId) throws HBaseException {
        List<Criteria.Expression> otherExpressions = new ArrayList<Criteria.Expression>();

        if (startDate != null && !"".equals(startDate)) {
            Criteria.Expression expression = Criteria.lse("data.reportTime", Long.MAX_VALUE - Long.valueOf(startDate));
            otherExpressions.add(expression);
        }
        if (endDate != null && !"".equals(endDate)) {
            Criteria.Expression expression = Criteria.gte("data.reportTime", Long.MAX_VALUE - Long.valueOf(endDate));
            otherExpressions.add(expression);
        }
        if (tenantId != null && !"".equals(tenantId)) {
            Criteria.Expression expression = Criteria.gte("data.tenantId", tenantId);
            otherExpressions.add(expression);
        }

        Criteria.Expression otherCondition = Criteria.and(otherExpressions);
        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, dataCf);
        query.getOptions().setPageSize(-1);

        List<VehicleStatusInfoTable> result = query.where(otherCondition).execute();

        return result;
    }

    public List<VehicleStatusInfoTable> loadVehStatusInfoByTime(String vin, String tenantId, String eventId, Long startDate, Long endDate) throws HBaseException {

        List<Criteria.Expression> otherExpressions = new ArrayList<Criteria.Expression>();
        if (startDate != null && !"".equals(startDate)) {
            Criteria.Expression expression = Criteria.lse("data.reportTime", Long.MAX_VALUE - Long.valueOf(startDate));
            otherExpressions.add(expression);
        }
        if (endDate != null && !"".equals(endDate)) {
            Criteria.Expression expression = Criteria.gte("data.reportTime", Long.MAX_VALUE - Long.valueOf(endDate));
            otherExpressions.add(expression);
        }
        if (StringUtils.isNotBlank(vin)) {
            Criteria.Expression expression = Criteria.eq("data.vin", vin);
            otherExpressions.add(expression);
        }
        if (StringUtils.isNotBlank(tenantId)) {
            Criteria.Expression expression = Criteria.eq("data.tenantId", tenantId);
            otherExpressions.add(expression);
        }
        if (StringUtils.isNotBlank(eventId)) {
            Criteria.Expression expression = Criteria.eq("data.eventId", eventId);
            otherExpressions.add(expression);
        }
        Criteria.Expression otherCondition = Criteria.and(otherExpressions);
        Query<VehicleStatusInfoTable> query = new Query<VehicleStatusInfoTable>(this, VehicleStatusInfoTable.class, allCf);
        query.getOptions().setPageSize(-1);

        return query.where(otherCondition).execute();
    }
}
