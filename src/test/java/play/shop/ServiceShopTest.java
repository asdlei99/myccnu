package play.shop;

import org.junit.Assert;
import org.junit.Test;

public class ServiceShopTest {

    private final ServiceShop serviceShop = new ServiceShop();


    @Test
    public void testGetOne() throws Exception {
        Assert.assertNotNull(serviceShop.getOne(1));
    }

}
