package com.cn.service.impl;

import com.cn.dao.IMessageDao;
import com.cn.model.Message;
import com.cn.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {


    @Autowired
    private IMessageDao messageDao;



    @Override
    public List<Message> getMessagePageByEntity(Message message){
        return messageDao.pageList(message);
    }


    @Override
    public Message getMessageByEntity(Message message) {
        return messageDao.find(message);
    }
    @Override
    public void addMessage(Message message) {
        messageDao.insert(message);
    }

    @Override
     public void modifyMessage(Message message) {
        messageDao.update(message);
    }
    @Override
    public void  deleteMessage(Message message) {
        messageDao.delete(message);
    }



    public IMessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

}
