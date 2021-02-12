package Lab4.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

//    List<AddressBook> findByLastName(String lastName);

    AddressBook findById(long id);


}
