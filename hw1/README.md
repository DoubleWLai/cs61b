# [HW 1: Packages, Interfaces, Generics, Exceptions, Iteration](https://sp18.datastructur.es/materials/hw/hw1/hw1)

## Introduction

Implement all the methods:

In this homework, you will learn how to write and use packages, as well as get some hands-on practice with interfaces and abstract classes. We’ll also get an opportunity to implement a simple data structure as well as an algorithm that’s easy to implement using that data structure. Finally, we’ll add support for iteration and exceptions (which we’ll cover on Friday) to our data structure.

As mentioned in class, a package is a namespace that organizes a set of related classes and interfaces. Conceptually, you can think of packages as being similar to different folders on your computer. When you are building a large system, it is a good idea to organize it into different packages.

For this assignment, we’ll create a synthesizer package intended for use by programs that want to simulate the sound of instruments.

The synthesizer package has four components:

BoundedQueue, an interface which declares all the methods that must be implemented by any class that implements BoundedQueue.
AbstractBoundedQueue, an abstract class which implements BoundedQueue, capturing the redundancies between methods in BoundedQueue.
ArrayRingBuffer, a class which extends AbstractBoundedQueue and uses an array as the actual implementation of the BoundedQueue.
GuitarString, which uses an ArrayRingBuffer<Double> to implement the Karplus-Strong algorithm to synthesize a guitar string sound.

### Task 1: BoundedQueue

We will start by defining a BoundedQueue interface. The BoundedQueue is similar to our Deque from Project 1, but with a more limited API. Specifically, items can only be enqueued at the back of the queue, and can only be dequeued from the front of the queue. Unlike our Deque, the BoundedDeque has a fixed capacity, and nothing is allowed to enqueue if the queue is full.

Create a file BoundedQueue.java in the synthesizer folder. You can do this easily in IntelliJ by right-clicking on the synthesizer folder in the project structure sidebar and click New -> Java Class. Be sure to set “Kind” to “Interface”.

### Task 2: AbstractBoundedQueue

Create a new abstract class in a .java file called AbstractBoundedQueue.java that implements BoundedQueue. Your AbstractBoundedQueue class should have the following methods and fields 

### Task 3: ArrayRingBuffer

The ArrayRingBuffer class will do all the real work by extending AbstractBoundedQueue. That means we can happily inherit capacity(), fillCount(), isEmpty(), and isFull() without having to override these, but we’ll need to override all of the the abstract methods. In this part, you’ll fill out ArrayRingBuffer.java. You’ll need to rename the file from ArrayRingBuffer.java.skeleton to ArrayRingBuffer.java.

A naive array implementation of a BoundedQueue would store the newest item at position 0, the second newest item in position 1, and so forth. This is an inefficient approach, as we see in the example below, where the comments show entries 0, 1, 2, and 3 of the array respectively. We assume that the array is initially all nulls.

### Task 4: GuitarString

Finally, we want to flesh out GuitarString, which uses an ArrayRingBuffer to replicate the sound of a plucked string. We’ll be using the Karplus-Strong algorithm, which is quite easy to implement with a BoundedQueue.

The Karplus-Algorithm is simply the following three steps:

1.Replace every item in a BoundedQueue with random noise (double values between -0.5 and 0.5).
2.Remove the front double in the BoundedQueue and average it with the next double in the BQ (hint: use dequeue() and peek()) multiplied by an energy decay factor of 0.996.
3.Play the double that you dequeued in step 2. Go back to step 2 (repeating forever).
