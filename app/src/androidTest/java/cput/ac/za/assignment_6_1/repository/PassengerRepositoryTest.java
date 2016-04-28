package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.Passenger;
import cput.ac.za.assignment_6_1.repository.impl.PassengerRepositoryImp;

/**
 * Created by mgijma on 2016/04/28.
 */
public class PassengerRepositoryTest extends AndroidTestCase {


    private static final String TAG="BUS STATION TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PassengerRepository repo = new PassengerRepositoryImp(this.getContext());

        // CREATE
        Passenger createEntity = new Passenger.Builder()
                .name("Siphiwo")
                .surname("Mgijima")
                .age("23")
                .build();

        Passenger insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Passenger> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        Passenger entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Passenger updateEntity = new Passenger.Builder()
                .name("Siphiwo")
                .surname("Mgijima")
                .age("23")
                .build();

        repo.update(updateEntity);
        Passenger newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","23",newEntity.getAge());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Passenger deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG + " DELETE", deletedEntity);


    }
}
