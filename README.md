什么是Hook
Hook 英文翻译过来就是「钩子」的意思，那我们在什么时候使用这个「钩子」呢？在 Android 操作系统中系统维护着自己的一套事件分发机制。
应用程序，包括应用触发事件和后台逻辑处理，也是根据事件流程一步步地向下执行。而「钩子」的意思，就是在事件传送到终点前截获并监控事件的传输，
像个钩子钩上事件一样，并且能够在钩上事件时，处理一些自己特定的事件。

所以，我们自己创建代理对象，然后把原始对象替换为我们的代理对象，那么就可以在这个代理对象为所欲为了。

这里我们hook Activity的startActivity方法。

首先我们得找到被Hook的对象，我称之为Hook点；什么样的对象比较好Hook呢？自然是容易找到的对象。
什么样的对象容易找到？静态变量和单例；在一个进程之内，静态变量和单例变量是相对不容易发生变化的，因此非常容易定位，而普通的对象则要么无法标志，
要么容易改变。我们根据这个原则找到所谓的Hook点。

这里我们找到了Activity里的mInstrumentation字段，这里用到了反射和静态代理。

