package com.github.service.utils;

import com.github.pagehelper.cache.GuavaCache;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * https://www.eolink.com/news/post/29296.html
 * Guava cache
 * RateLimiter（限流器）
 * Guava Retry（重试）
 * EventBus
 * Guava 针对于每一种集合都提供了的静态工具类。
 * Collection               Collections2
 * List                     Lists
 * Set                      Sets
 * SortedSet                Sets
 * Map                      Maps
 * SortedMap                Maps
 * Queue                    Queues
 * Multiset                 Multisets
 * Multimap                 Multimaps
 * BiMap                    Maps
 * Table                    Tables
 */
public class GuavaUtil {
    List<String> list = Lists.newArrayList();
}
