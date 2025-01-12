package katas.exercises;

import katas.exercises.RoundRobinLoadBalancer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class RoundRobinLoadBalancerTest {

    @Test
    void testAddAndRouteRequest() {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        RoundRobinLoadBalancer.IP server1 = new RoundRobinLoadBalancer.IP("192.168.0.1");
        RoundRobinLoadBalancer.IP server2 = new RoundRobinLoadBalancer.IP("192.168.0.2");

        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);

        // First request should go to server1
        assertEquals(server1, loadBalancer.routeRequest());

        // Second request should go to server2
        assertEquals(server2, loadBalancer.routeRequest());

        // Third request should go back to server1 (round-robin)
        assertEquals(server1, loadBalancer.routeRequest());
    }

    @Test
    void testRemoveServer() {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        RoundRobinLoadBalancer.IP server1 = new RoundRobinLoadBalancer.IP("192.168.0.1");
        RoundRobinLoadBalancer.IP server2 = new RoundRobinLoadBalancer.IP("192.168.0.2");
        RoundRobinLoadBalancer.IP server3 = new RoundRobinLoadBalancer.IP("192.168.0.3");

        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);
        loadBalancer.addServer(server3);

        loadBalancer.removeServer(server2);

        // After removing server2, the requests should go from server1 -> server3 -> server1, etc.
        assertEquals(server1, loadBalancer.routeRequest());
        assertEquals(server3, loadBalancer.routeRequest());
        assertEquals(server1, loadBalancer.routeRequest());
    }

    @Test
    void testRouteRequestWhenNoServers() {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        assertNull(loadBalancer.routeRequest()); // Should return null when no servers are available
    }

    @Test
    void testRouteRequestAfterServerRemoval() {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        RoundRobinLoadBalancer.IP server1 = new RoundRobinLoadBalancer.IP("192.168.0.1");
        RoundRobinLoadBalancer.IP server2 = new RoundRobinLoadBalancer.IP("192.168.0.2");

        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);

        loadBalancer.removeServer(server1);

        // Only server2 should remain
        assertEquals(server2, loadBalancer.routeRequest());
        assertEquals(server2, loadBalancer.routeRequest()); // Repeated requests should always route to server2
    }

    @Test
    void testInvalidIP() {
        // Test that invalid IP address throws an exception
        assertThrows(IllegalArgumentException.class, () -> new RoundRobinLoadBalancer.IP("999.999.999.999"));
    }

    @Test
    void testValidIP() {
        // Test that a valid IP address does not throw an exception
        assertDoesNotThrow(() -> new RoundRobinLoadBalancer.IP("192.168.0.1"));
    }

    @Test
    void testRouteRequestWithMultipleServers() {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        RoundRobinLoadBalancer.IP server1 = new RoundRobinLoadBalancer.IP("192.168.0.1");
        RoundRobinLoadBalancer.IP server2 = new RoundRobinLoadBalancer.IP("192.168.0.2");
        RoundRobinLoadBalancer.IP server3 = new RoundRobinLoadBalancer.IP("192.168.0.3");
        RoundRobinLoadBalancer.IP server4 = new RoundRobinLoadBalancer.IP("192.168.0.4");

        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);
        loadBalancer.addServer(server3);
        loadBalancer.addServer(server4);

        // Routing should follow round-robin order
        assertEquals(server1, loadBalancer.routeRequest());
        assertEquals(server2, loadBalancer.routeRequest());
        assertEquals(server3, loadBalancer.routeRequest());
        assertEquals(server4, loadBalancer.routeRequest());
        assertEquals(server1, loadBalancer.routeRequest()); // After server4, it should go back to server1
    }
}
