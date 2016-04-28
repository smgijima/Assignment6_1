package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.User;
import cput.ac.za.assignment_6_1.repository.impl.UserRepositoryImpl;

/**
 * Created by mgijma on 2016/04/28.
 */
public class UserRepositoryTest extends AndroidTestCase {

    private static final String TAG="BUS STATION TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        UserRepository repo = new UserRepositoryImpl(this.getContext());

        // CREATE
        User createEntity = new User.Builder()
                .Userid("123")
                .Password("1111")
                .UserType("Premium")
                .build();

        User insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<User> area = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", area.size() > 0);

        //READ ENTITY
        User entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        User updateEntity = new User.Builder()
                .Userid("123")
                .Password("1111")
                .UserType("Premium")
                .build();


        repo.update(updateEntity);
        User newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "1111", newEntity.getPassword());

        // DELETE ENTITY
        repo.delete(updateEntity);
        User deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG + " DELETE", deletedEntity);
    }

}
