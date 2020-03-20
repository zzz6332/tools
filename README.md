
# HttpUtil

使用方法：HttpUtil.sendHttpWithUrlConnection(final String address, final List<String> mneed_list, final httpCallBackListener listener)

address：url地址

mneed_list：想要获得的value对应的key的集合

listener：回调OnFinish和OnError.

# ThreadPoolUtil

ThreadPoolUtil util = ThreadPoolUtil.getInstance();

util.execute(//Runnable对象);



# ImageLoader

使用方法：ImageLoader loader = new ImageLoader(这里传入缓存的策略);

缓存策略：内存缓存，本地缓存，内存和本地缓存。

