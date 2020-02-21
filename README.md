## LightNovel （轻小说）

本软件是基于 轻之国度论坛 的轻小说阅读软件。



## 功能实现



| 功能              | 工具                             | 代码中实现位置                                               |
| :---------------- | -------------------------------- | ------------------------------------------------------------ |
| 实现自定义ToolBar | AppBarLayout + ToolBar           | MainActivty.java+activity_main.xml                           |
| 实现列表下拉刷新  | SwipeRefreshLayout               | ArticlesFragment.java+fragment_articles.xml以及PlateFragment.java+fragment_plate |
| 实现多页滑动效果  | ViewPager + Fragment + TabLayout | MainActivity.java+MainAdapter.java+activity_main.xml         |
| 侧滑栏效果        | DrawerLayout + NavigationView    | MainActivty.java+布局文件                                    |
| 加载效果          | ProgressBar                      | ArticlesFragment.java+PlateFragment.java                     |
| 网络请求          | Okhttp3                          | ArticlesFragment.java+PlateFragment.java                     |
| 解析html          | Jsoup                            | ArticlesFragment.java+PlateFragment.java                     |
| webview           | webview                          | ForumActivity                                                |

## 第三方库使用

```java
    implementation 'com.google.android.material:material:1.0.0'
    implementation("com.squareup.okhttp3:okhttp:4.3.1")
    implementation 'org.jsoup:jsoup:1.12.1'
    implementation 'com.github.bumptech.glide:glide:4.5.0'
    implementation 'com.drakeet.multitype:multitype:4.2.0'
    implementation 'de.hdodenhof:circleimageview:3.0.1'
```



较为遗憾的地方：并未完成当网页图文混杂时，Android的页面设计。