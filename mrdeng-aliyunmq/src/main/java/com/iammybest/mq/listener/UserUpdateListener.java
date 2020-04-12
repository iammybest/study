package com.iammybest.mq.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.iammybest.common.log.Logger;

/**
 * Created by MrDeng on 2017/2/18.
 */
public class UserUpdateListener implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        Logger.i(this.getClass(),"**UserUpdateListener Listen**"+message.getKey()+" Tag"+message.getTag());
        return Action.CommitMessage;
    }
}
