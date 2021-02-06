# ThreadSamples

The two progams uploaded gives a basic understanding of how threads work and as well as why they are important.
The file producerConsumer.java and teacherStudentWhiteboard.java are examples of multi-process synchronization problems that demonstrates the need for synchronization and the complications that comes with it when using multiple threads.

Overview
---------
The Producer Consumer Problem are in essence two cyclic processes that share a fixed sized buffer that is used as a queue. The Producer job is to create data by writing to the buffer and the Consumer role to read from that data that is produce by the producer and remove the data after using it.

The Teacher Student Whiteboard problem are also two cyclic processes that share a common object that is used for communicating. The Teacher role is to write to the Whiteboad and the Student job is to read from the Whiteboard.
