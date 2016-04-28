package cput.ac.za.assignment_6_1.repository;
import java.util.Set;
/**
 * Created by mgijma on 2016/04/20.
 */
public interface Repository<W, ID> {

    W findById(ID id);

    W save(W entity);

    W update(W entity);

    W delete(W entity);

    Set<W> findAll();

    int deleteAll();
}