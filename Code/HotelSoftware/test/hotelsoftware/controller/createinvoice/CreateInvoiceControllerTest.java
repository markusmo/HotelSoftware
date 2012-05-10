/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.PartyFacade;
import hotelsoftware.model.domain.parties.PrivateCustomer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class CreateInvoiceControllerTest {
    
    public CreateInvoiceControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CreateInvoiceController.
     */
    @Test
    public void testGetInstance() { //:S
        System.out.println("getInstance");
        CreateInvoiceController expResult = CreateInvoiceController.getInstance();
        CreateInvoiceController result = CreateInvoiceController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of search method, of class CreateInvoiceController.
     * TODO: SearchState.search() noch nicht implementiert
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String firstName = "";
        String lastName = "";
        String roomNr = "";
        CreateInvoiceController instance = CreateInvoiceController.getInstance();
        Collection expResult = null;
        Collection result = instance.search(firstName, lastName, roomNr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectHabitations method, of class CreateInvoiceController.
     */
    @Test
    public void testSelectHabitations() {
        System.out.println("selectHabitations");
        CreateInvoiceController instance = CreateInvoiceController.getInstance();
        Collection<HabitationData> habitations = null;
        instance.selectHabitations(habitations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedHabitations method, of class CreateInvoiceController.
     */
    @Test
    public void testGetSelectedHabitations() {
        System.out.println("getSelectedHabitations");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getSelectedHabitations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectItems method, of class CreateInvoiceController.
     */
    @Test
    public void testSelectItems() {
        System.out.println("selectItems");
        Collection<InvoiceItemData> items = null;
        CreateInvoiceController instance = null;
        instance.selectItems(items);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelItems method, of class CreateInvoiceController.
     */
    @Test
    public void testCancelItems() {
        System.out.println("cancelItems");
        InvoiceItemData item = null;
        int amount = 0;
        CreateInvoiceController instance = null;
        instance.cancelItems(item, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCountries method, of class CreateInvoiceController.
     */
    @Test
    public void testGetAllCountries() {
        System.out.println("getAllCountries");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getAllCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCompanyCustomer method, of class CreateInvoiceController.
     */
    @Test
    public void testCreateCompanyCustomer_8args() {
        System.out.println("createCompanyCustomer");
        String companyName = "";
        String street = "";
        String city = "";
        String zip = "";
        String email = "";
        String phone = "";
        String fax = "";
        CountryData country = null;
        CreateInvoiceController instance = null;
        instance.createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCompanyCustomer method, of class CreateInvoiceController.
     */
    @Test
    public void testCreateCompanyCustomer_15args() {
        System.out.println("createCompanyCustomer");
        String companyName = "";
        String street = "";
        String city = "";
        String zip = "";
        String email = "";
        String phone = "";
        String fax = "";
        CountryData country = null;
        String invoiceStreet = "";
        String invoiceCity = "";
        String invoiceZip = "";
        String invoiceEmail = "";
        String invoicePhone = "";
        String invoiceFax = "";
        CountryData invoiceCountry = null;
        CreateInvoiceController instance = null;
        instance.createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, invoiceStreet, invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPrivateCustomer method, of class CreateInvoiceController.
     */
    @Test
    public void testCreatePrivateCustomer_9args() throws PrivateCustomerNotFoundException, GuestNotFoundException {
        System.out.println("createPrivateCustomer");
        String firstName = "test";
        String lastName = "user";
        String street = "Experimentierstraße";
        String city = "Nibiru";
        String zip = "2345";
        String email = "testestest";
        String phone = "023452345";
        String fax = "23453425";
        PrivateCustomer testCustomer = PrivateCustomer.create(firstName, lastName, 'w', null, null);
        CountryData country = null;
        CreateInvoiceController instance = null;
        instance.createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country);
        PartyFacade facadeInstance = PartyFacade.getInstance();
        PrivateCustomer assertCustomer = facadeInstance.getPrivateCustomerByName(firstName, lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPrivateCustomer method, of class CreateInvoiceController.
     */
    @Test
    public void testCreatePrivateCustomer_16args() {
        System.out.println("createPrivateCustomer");
        String firstName = "";
        String lastName = "";
        String street = "";
        String city = "";
        String zip = "";
        String email = "";
        String phone = "";
        String fax = "";
        CountryData country = null;
        String invoiceStreet = "";
        String invoiceCity = "";
        String invoiceZip = "";
        String invoiceEmail = "";
        String invoicePhone = "";
        String invoiceFax = "";
        CountryData invoiceCountry = null;
        CreateInvoiceController instance = null;
        instance.createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, invoiceStreet, invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorkingHabitationsGuests method, of class CreateInvoiceController.
     */
    @Test
    public void testGetWorkingHabitationsGuests() {
        System.out.println("getWorkingHabitationsGuests");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getWorkingHabitationsGuests();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useGuestAsCustomer method, of class CreateInvoiceController.
     */
    @Test
    public void testUseGuestAsCustomer() {
        System.out.println("useGuestAsCustomer");
        GuestData guest = null;
        CreateInvoiceController instance = null;
        instance.useGuestAsCustomer(guest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChosenItems method, of class CreateInvoiceController.
     */
    @Test
    public void testGetChosenItems() {
        System.out.println("getChosenItems");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getChosenItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pay method, of class CreateInvoiceController.
     */
    @Test
    public void testPay() {
        System.out.println("pay");
        CreateInvoiceController instance = null;
        instance.pay();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class CreateInvoiceController.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        CreateInvoiceController instance = null;
        instance.next();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of back method, of class CreateInvoiceController.
     */
    @Test
    public void testBack() {
        System.out.println("back");
        CreateInvoiceController instance = null;
        instance.back();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpenItems method, of class CreateInvoiceController.
     */
    @Test
    public void testGetOpenItems() {
        System.out.println("getOpenItems");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getOpenItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHabitations method, of class CreateInvoiceController.
     */
    @Test
    public void testGetHabitations() {
        System.out.println("getHabitations");
        CreateInvoiceController instance = CreateInvoiceController.getInstance();
        Collection expResult = DBHabitation.search(201); //derzeit einzige habitation, zur Sicherheit in DB nachschauen
        //Collection expResult = null;
        Collection result = instance.getHabitations();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHabitations method, of class CreateInvoiceController.
     */
    @Test
    public void testSetHabitations() {
        System.out.println("setHabitations");
        Collection<Habitation> habitations = null;
        CreateInvoiceController instance = null;
        instance.setHabitations(habitations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedItems method, of class CreateInvoiceController.
     */
    @Test
    public void testGetSelectedItems() {
        System.out.println("getSelectedItems");
        CreateInvoiceController instance = null;
        Collection expResult = null;
        Collection result = instance.getSelectedItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedItems method, of class CreateInvoiceController.
     */
    @Test
    public void testSetSelectedItems() {
        System.out.println("setSelectedItems");
        Collection<InvoiceItem> selectedItems = null;
        CreateInvoiceController instance = null;
        instance.setSelectedItems(selectedItems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class CreateInvoiceController.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        CreateInvoiceController instance = null;
        CreateInvoiceState expResult = null;
        CreateInvoiceState result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class CreateInvoiceController.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        CreateInvoiceState state = null;
        CreateInvoiceController instance = null;
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}