import threading


class Simple(object):
    def __init__(self):
        self.c = threading.Condition()
        self.t = 0
        self.l1 = threading.Lock()
        self.l1.acquire()
        self.l2 = threading.Lock()
        self.l2.acquire()
        self.s1 = threading.Semaphore(0)
        self.s2 = threading.Semaphore(0)

    def first(self, printFirst: 'Callable[[], None]') -> None:
        # self.res(0, printFirst)
        printFirst()
        self.l1.release()
        self.s1.release()

    def second(self, printSecond: 'Callable[[], None]') -> None:
        # self.res(1, printSecond)
        self.l1.acquire()
        self.s1.acquire()
        printSecond()
        self.l2.release()
        self.s2.release()

    def third(self, printThird: 'Callable[[], None]') -> None:
        # self.res(2, printThird)
        self.l2.acquire()
        self.s2.acquire()
        printThird()

    def res(self, val: int, func: 'Callable[[], None]') -> None:
        with self.c:
            self.c.wait_for(lambda: val == self.t)  # 参数是函数对象，返回值是bool类型
            func()
            self.t += 1
            self.c.notify_all()
