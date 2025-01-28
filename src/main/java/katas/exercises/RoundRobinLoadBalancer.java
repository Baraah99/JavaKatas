package katas.exercises;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancer {
    /**
     * In distributed systems, a load balancer is responsible for distributing incoming requests to multiple server instances.
     * A round-robin load balancer assigns requests to server instances in a circular order.
     */
    private List<IP> servers;
    private int currentIndex;

    /**
     * Constructor to initialize the load balancer.
     */
    public RoundRobinLoadBalancer() {
        this.servers = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Adds a server instance to the load balancer.
     *
     * @param server the IP object representing the server to add
     */
    public void addServer(IP server) {
        servers.add(server);
    }

    /**
     * Removes a server instance from the load balancer.
     *
     * @param server the IP object representing the server to remove
     */
    public void removeServer(IP server) {
        servers.remove(server);
        // Reset the current index to prevent index out-of-bounds errors
        if (currentIndex >= servers.size()) {
            currentIndex = 0;
        }
    }

    /**
     * Routes a request to the next server in round-robin order.
     *
     * @return the IP object of the server handling the request, or null if no servers are available
     */
    public IP routeRequest() {
        if (servers.isEmpty()) {
            return null; // No servers available
        }
        IP server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        return server;
    }

    public static void main(String[] args) {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        loadBalancer.addServer(new IP("192.168.0.1"));
        loadBalancer.addServer(new IP("192.168.0.2"));
        loadBalancer.addServer(new IP("192.168.0.3"));

        System.out.println("Routing to: " + loadBalancer.routeRequest());
        System.out.println("Routing to: " + loadBalancer.routeRequest());
        System.out.println("Routing to: " + loadBalancer.routeRequest());
        System.out.println("Routing to: " + loadBalancer.routeRequest());

        loadBalancer.removeServer(new IP("192.168.0.2"));

        System.out.println("Routing to: " + loadBalancer.routeRequest());
        System.out.println("Routing to: " + loadBalancer.routeRequest());
    }

    /**
     * Represents an IP address.
     */
    static class IP {
        private final String address;

        /**
         * Constructor to initialize an IP address.
         *
         * @param address the IP address as a string
         */
        public IP(String address) {
            if (!isValidIP(address)) {
                throw new IllegalArgumentException("Invalid IP address: " + address);
            }
            this.address = address;
        }

        /**
         * Validates an IPv4 address.
         *
         * @param address the IP address to validate
         * @return true if the address is valid, false otherwise
         */
        private static boolean isValidIP(String address) {
            String[] parts = address.split("\\.");
            if (parts.length != 4) {
                return false;
            }
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part);
                    if (num < 0 || num > 255) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return address;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            IP ip = (IP) obj;
            return address.equals(ip.address);
        }

        @Override
        public int hashCode() {
            return address.hashCode();
        }
    }
}
