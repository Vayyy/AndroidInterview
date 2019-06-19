# AndroidInterview
面试题

  ArrayList和LinkedList的大致区别
1.ArrayList是实现了基于动态数组的数据结构，LinkedList是基于链表结构。
2.对于随机访问的get和set方法，ArrayList要优于LinkedList，因为LinkedList要移动指针。
3.对于新增和删除操作add和remove，LinkedList比较占优势，因为ArrayList要移动数据
1. Vector & ArrayList 
1）  Vector的方法都是同步的(Synchronized),是线程安全的(thread-safe)，而ArrayList的方法不是，由于线程的同步必然要影响性能，因此,ArrayList的性能比Vector好。 
2） 当Vector或ArrayList中的元素超过它的初始大小时,Vector会将它的容量翻倍,而ArrayList只增加50%的大小，这样,ArrayList就有利于节约内存空间。

 java内存模型
 1.1.1 程序计数器

内存空间小，线程私有。字节码解释器工作是就是通过改变这个计数器的值来选取下一条需要执行指令的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖计数器完成

如果线程正在执行一个 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是 Native 方法，这个计数器的值则为 (Undefined)。此内存区域是唯一一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。

1.1.2 Java 虚拟机栈

线程私有，生命周期和线程一致。描述的是 Java 方法执行的内存模型：每个方法在执行时都会床创建一个栈帧(Stack Frame)用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每一个方法从调用直至执行结束，就对应着一个栈帧从虚拟机栈中入栈到出栈的过程。

局部变量表：存放了编译期可知的各种基本类型(boolean、byte、char、short、int、float、long、double)、对象引用(reference 类型)和 returnAddress 类型(指向了一条字节码指令的地址)

StackOverflowError：线程请求的栈深度大于虚拟机所允许的深度。
OutOfMemoryError：如果虚拟机栈可以动态扩展，而扩展时无法申请到足够的内存。

1.1.3 本地方法栈

区别于 Java 虚拟机栈的是，Java 虚拟机栈为虚拟机执行 Java 方法(也就是字节码)服务，而本地方法栈则为虚拟机使用到的 Native 方法服务。也会有 StackOverflowError 和 OutOfMemoryError 异常。

1.1.4 Java 堆

对于绝大多数应用来说，这块区域是 JVM 所管理的内存中最大的一块。线程共享，主要是存放对象实例和数组。内部会划分出多个线程私有的分配缓冲区(Thread Local Allocation Buffer, TLAB)。可以位于物理上不连续的空间，但是逻辑上要连续。

OutOfMemoryError：如果堆中没有内存完成实例分配，并且堆也无法再扩展时，抛出该异常。

1.1.5 方法区

属于共享内存区域，存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。


在Java语言里，可作为GC Roots对象的包括如下几种：
a.虚拟机栈(栈桢中的本地变量表)中的引用的对象
b.方法区中的类静态属性引用的对象
c.方法区中的常量引用的对象
d.本地方法栈中JNI的引用的对象 

.wait和.sleep区别
       （1）属于不同的两个类，sleep()方法是线程类（Thread）的静态方法，wait()方法是Object类里的方法。

       （2）sleep()方法不会释放锁，wait()方法释放对象锁。

       （3）sleep()方法可以在任何地方使用，wait()方法则只能在同步方法或同步块中使用。

       （4）sleep()必须捕获异常，wait()方法、notify()方法和notiftAll()方法不需要捕获异常。

       （5）sleep()使线程进入阻塞状态（线程睡眠），wait()方法使线程进入等待队列（线程挂起），也就是阻塞类别不同。

​​

synchronized和lock区别
1.首先synchronized是java内置关键字，在jvm层面，Lock是个java接口；
2.synchronized无法判断是否获取锁的状态，Lock可以判断是否获取到锁；
3.synchronized会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；
4.用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞，线程2则会一直等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了；
5.synchronized的锁可重入、不可中断、非公平，而Lock锁可重入、可判断、可公平（两者皆可）
6.Lock锁适合大量同步的代码的同步问题，synchronized锁适合代码少量的同步问题

  Hashtable和HashMap的区别： 
a)   继承不同。  public class Hashtable extends Dictionary implements Map public class HashMap extends  AbstractMap implements Map 
b)  Hashtable中的方法是同步的，而HashMap中的方法在缺省情况下是非同步的。在多线程并发的环境下，可以直接使用Hashtable，但是要使用HashMap的话就要自己增加同步处理了。 
c)  Hashtable 中， key 和 value 都不允许出现 null 值。 在 HashMap 中， null 可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为 null 。当 get() 方法返回 null 值时，即可以表示 HashMap 中没有该键，也可以表示该键所对应的值为 null 。因此，在 HashMap 中不能由 get() 方法来判断 HashMap 中是否存在某个键， 而应该用 containsKey() 方法来判断。 
d)  两个遍历方式的内部实现上不同。Hashtable、HashMap都使用了Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。 

e)  哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。

 f)  Hashtable和HashMap它们两个内部实现方式的数组的初始大小和扩容的方式。HashTable中hash数组默认大小是11，增加的方式是old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。   注：  HashSet子类依靠hashCode()和equal()方法来区分重复元素。      HashSet内部使用Map保存数据，即将HashSet的数据作为Map的key值保存，这也是HashSet中元素不能重复的原因。而Map中保存key值的,会去判断当前Map中是否含有该Key对象，内部是先通过key的hashCode,确定有相同的hashCode之后，再通过equals方法判断是否相同

Activity的onNewIntent()方法何时会被调用? 
前提:ActivityA已经启动过,处于当前应用的Activity堆栈中; 
当ActivityA的LaunchMode为SingleTop时，如果ActivityA在栈顶,且现在要再启动ActivityA，这时会调用onNewIntent()方法 
当ActivityA的LaunchMode为SingleInstance,SingleTask时,如果已经ActivityA已经在堆栈中，那么此时会调用onNewIntent()方法 
当ActivityA的LaunchMode为Standard时，由于每次启动ActivityA都是启动新的实例，和原来启动的没关系，所以不会调用原来ActivityA的onNewIntent方法
  Activity 的 Flags 有很多，标记位的作用有很多，有的标记位可以设定 Activity 的启动模式，有的可以影响 Activity 的运行状态。

FLAG_ACTIVITY_NEW_TASK 
指定 Activity 以 singleTask 模式启动
FLAG_ACTIVITY_SINGLE_TOP 
指定 Activity 以 singleTop 模式启动
FLAG_ACTIVITY_CLEAR_TOP 
具备此标记为的 Activity 在启动时会将位于同一任务栈的所有位于它上面的 Activity 出栈，这个标记位一般会和 singleTask一起出现，singleTask 启动模式默认就具有此标记位的效果
FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS 
具有这个标记的 Activity 不会出现在历史 Activity 列表中。

启动别的应用的activity
​​

​​

三、进程与线程的关系
它们之间的区别：
1、线程是进程的一部分，所以线程有的时候被称为是轻权进程或者轻量级进程。 
2、一个没有线程的进程是可以被看作单线程的，如果一个进程内拥有多个进程，进程的执行过程不是一条线（线程）的，而是多条线（线程）共同完成的。 
3、系统在运行的时候会为每个进程分配不同的内存区域，但是不会为线程分配内存（线程所使用的资源是它所属的进程的资源），线程组只能共享资源。那就是说，出了CPU之外（线程在运行的时候要占用CPU资源），计算机内部的软硬件资源的分配与线程无关，线程只能共享它所属进程的资源。 
4、与进程的控制表PCB相似，线程也有自己的控制表TCB，但是TCB中所保存的线程状态比PCB表中少多了。 
5、进程是系统所有资源分配时候的一个基本单位，拥有一个完整的虚拟空间地址，并不依赖线程而独立存在。
它们之间的联系：
简单的说就是：一个程序包含进程，进程又包含线程，线程是进程的一个组成部分，进程是操作系统分配资源的基本单位，线程是不会分配资源的，一个进程可以包含多个线程，然后这些线程共享进程的资源。
分开来说就是：
线程是进程的一个实体，是CPU 调度和分配的基本单位，其本身不拥有系统资源，只含有程序计数器、寄存器和栈等一些运行时必不可少的基本资源。同属一个进程的线程共享进程中的全部资源。
进程是系统资源分配时的一个基本单位，拥有一个完整的虚拟空间地址。
系统在运行的时候会为每个进程分配不同的内存区域。
线程组只能共享资源，即除了CPU外，计算机内部的软硬件资源的分配与线程无关，线程只能共享它所属进程的资源
  序列化
所谓的序列化指的是把对象转换成字节序列的过程，也可以称之为对象流，可以保存到文件中，也可以用来网络传输数据。
反序列化既是相反的过程，可以从我们的文件中把对象流（字节序列）读出来，转换为对象供我们使用
1）永久性保存对象，保存对象的字节序列到本地文件中；
2）通过序列化对象在网络中传递对象；
3）通过序列化在进程间传递对象
   两者区别在于存储媒介的不同。
Serializable使用IO读写存储在硬盘上。序列化过程使用了反射技术，并且期间产生临时对象。优点代码少。
Parcelable是直接在内存中读写，我们知道内存的读写速度肯定优于硬盘读写速度，所以Parcelable序列化方式性能上要优于Serializable方式很多。但是代码写起来相比Serializable方式麻烦一些。
通过比较发现，性能与简便我们只能选其一，大多数情况下使用Serializable也是没什么问题的，但是还是建议大家使用Parcelable方式实现序列化，毕竟性能好很多，其实也没多麻烦
  Binder机制由三部分组成，即：
1.Client;
2.Server;
3.ServiceManager。
​​

三部分组件之间的关系:
1.Client、Server、ServiceManager均在用户空间中实现，而Binder驱动程序则是在内核空间中实现的；
2.在Binder通信中，Server进程先注册一些Service到ServiceManager中，ServiceManager负责管理这些Service并向Client提供相关的接口；
3.Client进程要和某一个具体的Service通信，必须先从ServiceManager中获取该Service的相关信息，Client根据得到的Service信息与Service所在的Server进程建立通信，之后Clent就可以与Service进行交互了；
4.Binder驱动程序提供设备文件/dev/binder与用户空间进行交互，Client、Server和ServiceManager通过open和ioctl文件操作函数与Binder驱动程序进行通信；
5.Client、Server、ServiceManager三者之间的交互都是基于Binder通信的，所以通过任意两者这件的关系，都可以解释Binder的机制。

View动画改变的只是View的显示，而没有改变View的响应区域；而属性动画会通过反射技术来获取和执行属性的get、set方法，从而改变了对象位置的属性值。

为什么内部类使用的变量要final？
因为生命周期的原因。方法中的局部变量，方法结束后这个变量就要释放掉，final保证这个变量始终指向一个对象。首先，内部类和外部类其实是处于同一个级别，内部类不会因为定义在方法中就会随着方法的执行完毕而跟随者被销毁。问题就来了，如果外部类的方法中的变量不定义final，那么当外部类方法执行完毕的时候，这个局部变量肯定也就被GC了，然而内部类的某个方法还没有执行完，这个时候他所引用的外部变量已经找不到了。如果定义为final，java会将这个变量复制一份作为成员变量内置于内部类中，这样的话，由于final所修饰的值始终无法改变，所以这个变量所指向的内存区域就不会变。 为了解决：局部变量的生命周期与局部内部类的对象的生命周期的不一致性问题

ThreadPoolExecutor 策略
这是ThreadPoolExecutor的构造函数，首先需要明白的是这几个参数的含义
A．    corePoolSize： 线程池维护线程的最少数量
B．    maximumPoolSize：线程池维护线程的最大数量
C．    keepAliveTime： 线程池维护线程所允许的空闲时间
D．    unit： 线程池维护线程所允许的空闲时间的单位
E．    workQueue： 线程池所使用的缓冲队列
F．    handler： 线程池对拒绝任务的处理策略

当一个任务通过asynct.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0)方法欲添加到线程池时：
如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。

也就是：处理任务的优先级为：
核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。

当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。

unit可选的参数为java.util.concurrent.TimeUnit中的几个静态属性：
NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS。

workQueue是BlockQueue的子类，ArrayBlockingQueue,DelayQueue

handler有四个选择(这不是android的Handler)：
ThreadPoolExecutor.AbortPolicy() – 这个也是AsyncTask.THREAD_POOL_EXECUTOR使用的
抛出java.util.concurrent.RejectedExecutionException异常
ThreadPoolExecutor.CallerRunsPolicy()
重试添加当前的任务，他会自动重复调用execute()方法
ThreadPoolExecutor.DiscardOldestPolicy()
抛弃旧的任务
ThreadPoolExecutor.DiscardPolicy()
抛弃当前的任务

String a = new String("aa")，代表在堆内存中，创建了一个字符串对象，变量a指向该对象，而该对象又指向在常量池中的字符串常量。创建了两个对象，一个是在常量池中，一个是在堆内存中，常量池的为"aa";堆内存中为new String();

而String a = "aa"代表直接由变量a指向常量池中的字符串，省去了中间的堆内存中的对象，因为new对象时，都会在堆中创建对象

   ==和qeual区别
  值类型是存储在内存中的堆栈（以后简称栈），而引用类型的变量在栈中仅仅是存储引用类型变量的地址，而其本身则存储在堆中。
    ==操作比较的是两个变量的值是否相等，对于引用型变量表示的是两个变量在堆中存储的地址是否相同，即栈中的内容是否相同。
    equals操作表示的两个变量是否是对同一个对象的引用，即堆中的内容是否相同。
    ==比较的是2个对象的地址，而equals比较的是2个对象的内容。

  Integer和int区别
1、Integer是int的包装类，int则是java的一种基本数据类型 
2、Integer变量必须实例化后才能使用，而int变量不需要 
3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
4、Integer的默认值是null，int的默认值是0
1.final
被final修饰的类是不可以被继承的，所以一个类不能即被abstract修饰又被final修饰，
被final修饰的变量和方法都是不能被更改的，继承之后子类也不能对父类的finala方法重写，但是可以重载
2. finally
finally是在Java异常处理时用到的，在try ,catch之后执行，不管有没有捕获到异常最后的finally方法肯定会得到执行
3.finalize()
方法名。Java 技术允许使用 finalize() 方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没有被引用时对这个对象调用的。它是在 Object 类中定义的，因此所有的类都继承了它。子类覆盖 finalize() 方法以整理系统资源或者执行其他清理工作。finalize() 方法是在垃圾收集器删除对象之前对这个对象调用的。注意：finalize不一定被jvm调用，只有当垃圾回收器要清除垃圾时才被调用


​​
​​

双重检验锁是对同步块加锁的方法。为什么会称为双重检验,因为有两次对 instance == null的检查，一次中同步块中一次中同步块外部。
对于两次instance的是否为空的判断解释：
1.为何在synchronization外面的判断？
       为了提高性能！如果拿掉这次的判断那么在行的时候就会直接的运行synchronization，所以这会使每个getInstance()都会得到一个静态内部锁，这样的话锁的获得以及释放的开销（包括上下文切换，内存同步等）都不可避免，降低了效率。所以在synchronization前面再加一次判断是否为空，则会大大降低synchronization块的执行次数。
2.为何在synchronization内部还要执行一次呢？
因为可能会有多个线程一起进入同步块外的 if，如果在同步块内不进行二次检验的话就会生成多个实例了。
 
PS：双重检验情况下，保存实例的唯一的静态变量要用volatile修饰，否则由于线程安全原因，一个类仍然有会生成多个实例

​​

GC Roots的条件：
1.	虚拟机栈的栈桢的局部变量表所引用的对象
1.	本地方法栈的JNI所引用的对象
1.	方法区的静态变量和常量所引用的对象



Java 的内存管理和引用类型
内存泄漏   内存泄漏2   内存溢出和内存泄漏的区别  Android 内存泄漏                  
JVM   适配器模式  代理模式    原型模式   工厂模式
常见设计模式
HTTP TCP/IP
Java注解   Java反射
Java 创建线程池
Android多线程编程    Android多线程编程面试题  java多线程 
线程的几种状态以及sleep,wait,yield,join的区别
Volatlie
List Set Map 区别
滑动冲突  滑动冲突2
四大组件--简书博主
IPC面试题  AIDL
Binder
View面试答案
19年校招面试题   面试题答案
动画
HandlerThread
子线程使用handler
线程池的工作原理及好处   4种线程池
IntentService  为什么不用BinderService来启动IntentService
图片加载
缓存更新策略
面向对象四大特性
String StringBuilder StringBuffer 区别
静态内部类和非静态内部类区别
重载和重写 
Finalized 匿名内部类访问局部变量为什么要加final
Object有哪些公共方法
自定义正方形布局
装箱和拆箱
异常处理
确保线程安全
synchnrizede和volatile区别
基本算法排序
rxjava  RxJava操作符
Android进阶
dagger
7大基础算法
BlockingQueue
HashMap实现原理
字节码 冷启动  Volatile原理  Synchronize原理  Synchronize原理2  CAS
 偏向锁、轻量级锁、自旋锁、重量级锁区别    红黑树   Hashmap1.8改动
 ConcurrentHashMap  HashMap线程安全问题   几个map比较 
 ReentrantLock   JVM垃圾回收  Binder
 
 Android中为什么主线程不会因为Looper.loop()里的死循环卡死？  2


广州视源
1 启动模式 触摸事件 为什么双重锁 工厂模式 自定义view  map  list set  触摸事件down livedata底层实现  静态工厂模式