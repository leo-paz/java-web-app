package Lab4.demo;

import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressBookController {

    private AddressBookRepository bookRepo;
    private BuddyInfoRepository buddyRepo;

    public AddressBookController(AddressBookRepository bookRepo, BuddyInfoRepository buddyRepo) {
        this.bookRepo = bookRepo;
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/addressbook")
    public String addressBook(@RequestParam(name="ID", required=true) Integer ID,
                              Model model) {
        model.addAttribute("ID", ID);

        AddressBook book = new AddressBook();
        book.setId(Long.valueOf(ID));
        bookRepo.save(book);

        return "addressbook";
    }

    @GetMapping("/addBuddy")
    public String addBuddy(@RequestParam(name="name", required=false, defaultValue="Bauer") String name,
                           @RequestParam(name="number", required=false, defaultValue="123") Integer number,
                           @RequestParam(name="ID", required=true) Integer ID,
                           @RequestParam(name="AddressBookID", required=false, defaultValue = "1") Integer AddressBookID,
                           Model model) {
        model.addAttribute("name", name);

        BuddyInfo buddyToAdd = new BuddyInfo(name, number);
        buddyToAdd.setId(Long.valueOf(ID));

        buddyRepo.save(buddyToAdd);

        AddressBook bookToAddToo = bookRepo.findById(AddressBookID);
        bookToAddToo.addBuddy(buddyToAdd);
        bookRepo.save(bookToAddToo);

        return "addBuddy";
    }

    @GetMapping("/removeBuddy")
    public String deleteBuddy(@RequestParam(name="ID", required=true) Integer ID,
                              @RequestParam(name="AddressBookID", required=true) Integer AddressBookID,
                              Model model) {
        model.addAttribute("ID", ID.toString());
        model.addAttribute("AddressBookID", AddressBookID.toString());

        BuddyInfo buddyInfoToRemove = buddyRepo.findById(ID);
        buddyRepo.delete(buddyInfoToRemove);

        AddressBook bookToRemoveFrom = bookRepo.findById(AddressBookID);

        bookToRemoveFrom.removeBuddy(buddyInfoToRemove);

        bookRepo.save(bookToRemoveFrom);

        return "removeBuddy";
    }

    @GetMapping("/getAddressBook")
    public String deleteBuddy(@RequestParam(name="ID", required=true) Integer ID,
                              Model model) {

        AddressBook book = bookRepo.findById(ID);

        model.addAttribute("book", book);

        return "getAddressBook";
    }

}
