package note;

/**
 * 设计模式:
 * 策略模式:定义了算法族, 分别封装起来, 让它们之间可以相互替换, 此模式让算法的变化独立于使用算法的客户.
 * 
 * 观察者模式:定义了对象之间的一对多依赖, 这样一来, 当一个对象改变状态时, 它的所有依赖者都会收到通知并自动更新.
 * Java内置了观察者模式, java.util.Observer(观察者), java.util.Observable(被观察者).
 * JDK中的Swing大量使用了观察者模式
 * 观察者和被观察者之间用松耦合方式结合, 被观察者不知道观察者的细节, 只知道观察者实现了观察者接口.
 * 使用此模式时, 你可以从被观察者处push或pull数据
 * 有多个观察者时, 不可以依赖特定的通知顺序
 * 缺点:要注意java.util.Observable实现上所带来的问题,因为它是一个抽象类而不是接口, 而且将关键的方法保护了起来,如setChanged()方法设置为了protected,只能继承,
 * 不能组合(组合下就不能使用setChanged()).所以不够灵活
 * 此模式也被应用在许多地方,例如JavaBeans, RMI(remote method invoke)
 * 
 * 装饰者模式:动态地将责任附加到对象上, 若要扩展功能, 装饰者提供了比继承更有弹性的替代方案.
 * 重要!装饰者和被装饰者有相同的超类型
 * Java io包中大量使用了装饰者模式, 抽象组件为InputStream,然后FileInputStream是被装饰的组件,Java io 程序库提供了几个组件,包括了FileInputStream, StringBufferInputStream,
 * ByteArrayInputStream等, 这些类都提供了最基本的字节读取功能. 而BufferInputStream是一个具体的装饰者, 它加入两种行为:利用缓冲输入来改进性能:用一个readline()方法来增强接口,
 * 再比如LineNumberInputStream也是一个具体的装饰者,它加上了计算行数的能力. 这些装饰者都继承自FilterInputStream,这是一个抽象装饰者,我们可以继承这个类来写自己的装饰者.
 * 你可以用无数个装饰者包装一个组件
 * 装饰者一般对组件的客户是透明的
 * 缺点:装饰者会导致设计中出现许多小对象, 如果过度使用, 会让程序变得很复杂.
 * 
 * 工厂模式:定义了创建对象的接口, 但由子类决定要实例化的类是哪一个. 工厂方法让类把实例化推迟到子类.
 * 产品类(Product)和创建者类(Creator)是平级的.
 * 抽象的Creator提供了一个创建对象的方法的接口, 也称为"工厂方法", 在抽象的Creator中, 任何其他实现的方法, 都可能使用到这个工厂方法所制造出来的产品, 但只有子类真正实现这个工厂方法.
 * 工厂方法(通常只有一个create方法)用来处理对象的创建, 并将这样的行为封装在子类中. 这样, 客户程序中关于超类的代码就和子类对象创建代码解耦了.
 * abstract Product factoryMethod(String type)
 * 工厂方法是抽象的, 所以依赖子类来处理对象的创建, 工厂方法必须返回一个产品. 超类中定义的方法, 通常使用到工厂方法的返回值, 工厂方法将客户和实际创建具体产品的代码分隔开来
 * 抽象工厂模式:提供一个接口, 用于创建相关或依赖对象的家族, (提供一个抽象接口来创建一个产品家族, 比如香料家族, 地区不同实现不同)而不需要明确指定具体类.(通常有多个create方法)
 * 
 * 单件模式:确保一个类只有一个实例, 并提供一个全局访问点.
 * 
 * 命令模式:将请求封装成对象, 以便使用不同的请求/队列或者日志来参数化其他对象. 命令模式也支持可撤销的操作.
 * 命令模式将发出请求的对象和执行请求的对象解耦.
 * 在被解耦的两者之间是通过命令对象进行沟通的, 命令对象封装了一个或一组动作.
 * 调用者通过调用命令对象的execute()发出请求, 这回使得接收者的动作被调用.
 * 调用者可以接受命令当做参数, 甚至在运行时动态地进行.
 * 命令可以支持撤销, 做法是实现一个undo方法来回到execute()被执行前的状态.
 * 宏命令是命令的一种简单延伸, 允许调用多个命令. 宏方法也支持撤销.
 * 
 * 适配器模式:将一个类的接口,转换成客户期望的另一个接口, 适配器让原本不兼容的类可以合作无间.
 * 当需要使用一个现有的类而其接口并不符合你的需要时, 就使用适配器
 * 适配器改变接口以符合客户的期望.
 * 适配器将一个对象包装起来以改变其接口, 装饰者将一个对象包装起来以增加新的行为和责任. 而外观将一群对象"包装"起来以简化其接口.
 * 
 * 外观模式:提供了一个统一的接口, 用来访问子系统中的一群接口.外观定义了一个高层接口, 让子系统更容易使用.
 * 当需要简化并统一一个很大的接口或者一群复杂的接口时, 使用外观.
 * 外观将客户从一个复杂的子系统中解耦.
 * 你可以为一个子系统实现一个以上的外观(很像数据库中的视图)
 * 
 * 模板方法模式:在一个方法中定义一个算法的骨架, 而将一些步骤延迟到子类中, 模板方法使得子类可以在不改变算法结构的情况下, 重新定义算法中的某些步骤.
 * 模板方法定义了一个算法的步骤, 并允许子类为一个或多个步骤提供实现.
 * hook钩子是一种被声明在抽象类中的方法, 但只有空的或者默认的实现. 钩子的存在, 可以让子类有能力对算法的不同点进行挂钩. 要不要挂钩, 由子类自行决定.
 * 为了防止子类改变模板方法中的算法, 可以将模板方法声明为final
 * JDK中的模板方法模式: Arrays.sort(),其中的参数必须实现Comparable接口,然后实现conpareTo()方法,这可能不太像标准的模板方法模式, 但毕竟继承Arrays类是不可能的. 还有InputStream
 * 中read(),由子类负责实现,然后该方法被read(byte[] b, int off, int len)调用.还有Swing中JFrame的paint()方法, 需要子类进行实现.
 * 
 * 迭代器模式:提供一种方法顺序访问一个聚合对象中的各个元素, 而又不暴露其内部的表示.
 * 
 * 组合模式:允许你将对象组合成树形结构来表现"整体/部分"层次结构, 组合能让客户以一致的方式处理个别对象以及对象组合.
 * 组合对象允许客户对个别对象以及组合对象一视同仁.
 * 组合结构内的任意对象称为组件, 组件可以是组合, 也可是叶节点, 所以说组合和叶节点必须实现同一个接口.
 * 
 * 状态模式:允许对象在内部状态改变时改变它的行为, 对象看起来好像修改了它的类.
 * 状态模式允许一个对象基于内部状态而拥有不同的行为.
 * Context会将行为委托给当前状态对象
 * 通过将每个状态封装进一个类, 我们把以后需要做得任何改变局部化了.
 * 
 * 代理模式:为另一个对象提供一个替身或者占位符以访问这个对象.
 * 代理模式为另一个对象提供代表, 以便控制客户对对象的访问, 管理访问的方式有许多种.
 * 远程代理管理客户和远程对象之间的交互.
 * 虚拟代理控制访问实例化开销大的对象.
 * 保护代理基于调用者控制对对象代理的访问.
 * 代理模式有许多变体, 例如:缓存代理, 同步代理, 防火墙代理和写入时复制代理.
 * 代理在结构上类似装饰者, 但是目的不同.
 * 装饰者模式为对象加上行为, 而代理则是控制访问
 * Java内置的代理支持, 可以根据需要建立动态代理, 并将所有调用分配到所选的处理器.
 * 就和其他的包装者一样, 代理会造成你的设计中类的数目增加.
 * 远程代理: 编写Server接口,该接口实现Remote接口, ServerImpl实现Server接口, 提供具体服务. 编写ServerDrive注册服务, 再编写ClientDrive使用服务. 运行:cd到classes目录, 运行
 * rmiregistry, 再运行ServerDrive, 再运行ClientDrive.
 *  
 * MVC:
 * 视图: 用来呈现模型. 视图通常直接从模型中取得它需要显示的状态与数据.
 * 控制器: 取得用户的输入并解读其对模型的意思.
 * 模型: 模型持有所有的数据, 状态和程序逻辑. 模型没有注意到视图和控制器, 虽然它提供了操作和检索状态的接口, 并发送状态改变通知给观察者.
 * ①你是用户-你和视图交互.
 * 视图是模型的窗口.当你对视图做一些事情时(比方说,按下播放按钮), 视图就告诉控制器你做了什么. 控制器会负责处理.
 * ②控制器要求模型改变状态.
 * 控制器解读你的动作.如果你按下某个按钮, 控制器会理解这个动作的意思, 并告知模型如何做出对应的动作.
 * ③控制器也可能要求视图做改变.
 * 当控制器从视图接收到某一动作, 结果可能是它也需要告诉视图改变其结果. 比方说,控制器可以将界面上的某些按钮或菜单项变成有效或者无效.
 * ④当模型状态改变时, 模型会通知视图.
 * 不管是你做了某些动作(比方说按下按钮)还是内部有了某些改变(比方说播放清单的下一首歌开始), 只要当模型内的东西改变时, 模型都会通知视图它的状态改变了.
 * ⑤视图向模型询问状态.
 * 视图直接从模型取得它显示的状态. 比方说, 当模型通知视图新歌开始播放时, 视图向模型询问歌名并显示出来, 当控制器请求视图改变时, 视图也可能向模型询问某些状态.
 * MVC是复合模式, 结合了观察者模式, 策略模式和组合模式.
 * 模型使用观察者模式, 以便观察者更新, 同时保持两者之间解耦.
 * 控制器是视图的策略, 视图可以使用不同的控制器实现, 得到不同的行为. 
 * 视图使用组合模式实现用户界面, 用户界面通常组合了嵌套的组件, 像面板,框架和按钮.
 * 这些模式携手合作, 把MVC模型的三层解耦, 这样可以保持设计干净又有弹性.
 * 适配器模式用来将新的模型适配成已有的视图和控制器.
 * Model 2是MVC在web上的应用.
 * 在Model 2中, 控制器实现成servlet, 而JSP/HTML实现视图.
 * 
 * 
 * 定义设计模式:模式是在某情境(context)下, 针对某问题的某种解决方案.
 * 情境就是应用某个模式的情况. 这应该是会不断出现的情况.
 * 问题就是你想在某情境下达到的目的, 但也可以是某情境下的约束.
 * 解决方案就是你所追求的:一个通用的设计, 用来解决约束,达到目的.
 * 
 * 其他设计模式:
 * 桥接(Bridge):使用桥接模式不只改变你的实现, 也改变你的抽象
 * 桥接优点:
 * 将实现予以解耦, 让它和界面不再永久绑定.
 * 抽象和实现可以独立扩展, 不会影响到对方.
 * 对于具体的抽象类所做的改变, 不会影响到客户.
 * 桥接的用途和缺点:
 * 适合使用在需要跨越多个平台的图形和窗口系统上.
 * 当需要不同的方式改变接口和实现时, 你会发现桥接模式很好用.
 * 桥接模式的缺点是增加了复杂度.
 * 
 * 生成器(Builder):使用生成器模式封装一个产品的构造过程, 并允许按步骤构造. JDK中有StringBuilder.
 * 生成器优点:
 * 将一个复杂对象的创建过程封装起来.
 * 允许对象通过多个步骤来创建, 并且可以改变过程(这和只有一个步骤的工厂模式不同)
 * 向客户隐藏产品内部的表现
 * 产品的实现可以被替换, 因为客户只看到一个抽象的接口
 * 生成器的用途和缺点:
 * 经常被用来创建组合结构
 * 与工厂模式相比, 采用生成器模式创建对象的客户, 需要具备更多的领域知识
 * 
 * 责任链(Chain of Responsibility):当你想让一个以上的对象有机会能够处理某个请求时, 就使用责任链模式
 * 责任链优点:
 * 将请求的发送者和接受者解耦
 * 可以简化你的对象, 因为它不要知道链的结构
 * 通过改变链内的成员或者调动它们的次序, 允许你动态地新增或者删除责任
 * 责任链的用途和缺点:
 * 经常被使用在窗口系统中, 处理鼠标和键盘之类的事件
 * 并不保证请求一定会被执行;如果没有任何对象处理它的话, 它可能会落到链尾端之外
 * 可能不容易观察运行时的特征, 有碍于除错
 * 
 * 中介者(Mediator): 使用中介者模式来集中相关对象之间复杂的沟通和控制方式
 * 中介者的优点:
 * 通过将对象彼此解耦, 可以增加对象的复用性
 * 通过将控制逻辑集中, 可以简化系统维护
 * 可以让对象之间所传递的消息变得简单而且大幅减少
 * 中介者的用途和缺点:
 * 中介者常常被用来协调相关的GUI组件
 * 如果设计不当, 中介者对象本身会显得过于复杂
 * 
 * 备忘录(Memento): 当你需要让对象返回之前的状态时(例如,你的用户请求"撤销"), 就使用备忘录模式
 * 备忘录模式有两个目标:
 * 存储系统关键对象的重要状态
 * 维护关键对象的封装
 * 请不要忘记单一责任原则, 不要把保持状态的工作和关键对象混为一起, 这样比较好. 这个专门掌握状态的对象, 就称为备忘录
 * 备忘录的优点:
 * 将被存储的状态放在外面, 不要和关键对象混为一起, 这可以帮助维护内聚
 * 保持关键对象的数据封装
 * 提供了容易实现的恢复能力
 * 备忘录的用途和缺点:
 * 备忘录用于存储状态
 * 存储和恢复状态的过程可能相当耗时
 * 在Java系统中, 其实可以考虑使用序列化机制存储系统的状态
 * ********************************************************************************************
 * 设计原则:
 * 封装变化
 * 多用组合,少用继承
 * 针对接口编程,不针对实现编程
 * 为交互对象之间的松耦合设计而努力
 * 对扩展开放, 对修改关闭
 * 要依赖抽象, 不要依赖具体类(依赖倒置原则):不能让高层组件依赖低层组件,而且, 不管高层或低层组件,"两者"都应该依赖于抽象,所谓高层组件,是由其他低层组件定义其行为的类
 * 变量不可以持有具体类的引用.不要让类派生自具体类.不要覆盖基类中已实现的方法.
 * 一个类应该只有一个引起变化的原因(这个原则告诉我们, 尽量让每个类保持单一责任)
 */
