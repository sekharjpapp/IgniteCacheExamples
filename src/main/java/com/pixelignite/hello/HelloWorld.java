package com.pixelignite.hello;

import com.pixelignite.WebController;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@Cacheable
public class HelloWorld {
    public static void main(String[] args) throws IgniteException {
        SpringApplication.run(HelloWorld.class,args);
        try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            CacheConfiguration<Integer,String> cfg =new CacheConfiguration<>();
            cfg.setName("demoCache");
            cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
            // Put values in cache.
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache(cfg);
            for (int i=0;i<10;i++){
                cache.put(i + 1,i +"Vv");

            }
            System.out.println(cache);
            /*cache.put(1, "Hello");
            cache.put(2, "World!");*/
            // Get values from cache
            // Broadcast 'Hello World' on all the nodes in the cluster.
            ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
            ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
            ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
            ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
        }


        }
    @Controller
    @Cacheable
    public class WebController {

        private  final Logger LOGGER = LoggerFactory.getLogger(com.pixelignite.WebController.class);

        /**
         * Each time the "{@code /}" URL is called, increment the hit counter
         * and indicate that the "{@code index.html}" page should be returned.
         *
         * @param httpSession The current session
         * @return The view to render, in MVC terms.
         */
        @RequestMapping(value = "/sessions")
        @Cacheable
        public String index(HttpSession httpSession) {
            Integer hits = (Integer) httpSession.getAttribute("hits");
            LOGGER.info("index() called, hits was '{}', session id '{}'", hits, httpSession.getId());
            if (hits == null) {
                hits = 0;
            }
            httpSession.setAttribute("hits", ++hits);
            return "index";
        }
    }
}
