package net.playtogether.jpa.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

@Component
public class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

	@Override
	public void customize(ConcurrentMapCacheManager cacheManager) {
		List<String> cachenames = Arrays.asList("sports", "meeting", "torneo");
		cacheManager.setCacheNames(cachenames);
	}
}