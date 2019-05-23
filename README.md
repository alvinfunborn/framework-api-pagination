#### 数据分页后端框架
1. 支持page+size
2. 支持cursor+size
3. 支持基于时间戳缓存的分页

##### usage
1. 若要使用基于时间戳缓存的分页, 需要实现ListCache

###### config
```$xslt
@Configuration
public class PaginatorConfig {

    @Bean
    public Paginator paginator() {
        return new StandardPaginator();
    }

    @Bean
    public CacheBasedPaginator cacheBasedPaginator() {
        return new StandardCacheBasedPaginator(new ListCache() {
            private final Map<String, List<Object>> CACHE_MAP = new ConcurrentHashMap<>();
            @Override
            public <T> List<T> get(String key, String timestamp) {
                return (List<T>) CACHE_MAP.getOrDefault(key + "_" + timestamp, Collections.emptyList());
            }

            @Override
            public <T> void cache(String key, String timestamp, List<T> list, long expire, TimeUnit timeUnit) {
                List<Object> cacheList = (List<Object>) list;
                CACHE_MAP.put(key + "_" + timestamp, cacheList);
            }
        });
    }
}
```

###### usage
```$xslt
@Service
public class TestService {

    @Autowired
    private Paginator paginator;
    @Autowired
    private CacheBasedPaginator cacheBasedPaginator;

    public void test() {
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println(paginator.paginate(list,
                new TablePageParam(2, 1)));
        System.out.println(paginator.paginate(list,
                new StreamPageParam(1, "2", new CommonMethodHandle<>(String.class, "toString", String.class, true))));
    }

    public List<String> list(String start) {
        return Arrays.asList(start, "2", String.valueOf(System.nanoTime()));
    }

    public void testCache() {
        ListMethodHandle<String> lister1 = new ListMethodHandle<>(this, "list", true, "1");
        System.out.println(cacheBasedPaginator.paginate(lister1,
                new TablePageParam(3, System.currentTimeMillis(), 1), "testService_list_1"));
        System.out.println(cacheBasedPaginator.paginate(lister1,
                new TablePageParam(2, System.currentTimeMillis(), 2), "testService_list_1"));
    }
}
```