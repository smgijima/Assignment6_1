package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.BusStation;
import cput.ac.za.assignment_6_1.repository.impl.BusStationRepositoryImpl;

/**
 * Created by mgijima on 2016/04/28.
 */
public class BusStationRepositoryTest extends AndroidTestCase {

    private static final String TAG="BUS STATION TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BusStationRepository repo = new BusStationRepositoryImpl(this.getContext());

        // CREATE
        BusStation createEntity = new BusStation.Builder()
                .busStationCode("0002")
                .Name("CPUT")
                .City("Cape Town")
                .Code("255")
                .build();

        BusStation insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BusStation> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        BusStation entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        BusStation updateEntity = new BusStation.Builder()
                .busStationCode("0002")
                .Name("CPUT")
                .City("Cape Town")
                .Code("255")
                .build();

        repo.update(updateEntity);
        BusStation newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","0002",newEntity.getBusStationCode());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BusStation deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG + " DELETE", deletedEntity);


    }
}
