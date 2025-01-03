package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.exceptions.RecipientNotFoundException;
import io.codeforall.bootcamp.javabank.persistence.dao.RecipientDao;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A {@link RecipientService} implementation
 */
@Service
public class RecipientServiceImpl implements RecipientService {

    private RecipientDao recipientDao;

    /**
     * Sets the recipient data access object
     *
     * @param recipientDao the recipient DAO to set
     */
    @Autowired
    public void setRecipientDao(RecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    /**
     * @see RecipientService#get(Integer)
     */
    @Override
    public Recipient get(Integer id) throws RecipientNotFoundException {

        Recipient recipient = recipientDao.findById(id);

        if (recipient == null) {
            throw new RecipientNotFoundException();
        }

        return recipient;
    }
}
