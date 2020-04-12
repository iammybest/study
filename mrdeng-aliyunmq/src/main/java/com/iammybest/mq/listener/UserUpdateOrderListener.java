package com.iammybest.mq.listener;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import com.iammybest.common.log.Logger;

/**
 * Created by MrDeng on 2017/2/18.
 */
public class UserUpdateOrderListener implements MessageOrderListener {
    @Override
    public OrderAction consume(Message message, ConsumeOrderContext consumeOrderContext) {
        Logger.i(this.getClass(),"**UserUpdateOrderListener Listen**"+message.getKey()+" Tag"+message.getTag());
        return OrderAction.Success;
    }
}
