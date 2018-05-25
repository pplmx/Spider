package individual.cy.douban.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: ezcaoyu
 * @date: 5/25/2018 09:10
 * Description:
 */
class GrabIpProxyPoolTest {

    private GrabIpProxyPool pool;

    @BeforeEach
    void setUp() {
        pool = Mockito.mock(GrabIpProxyPool.class);
    }

    @Test
    void common() {
        pool.common();
    }
}