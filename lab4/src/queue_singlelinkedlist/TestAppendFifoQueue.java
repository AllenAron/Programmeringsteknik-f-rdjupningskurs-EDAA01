package queue_singlelinkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	void testTwoEmptyQueues() {
		try {
			q1.append(q2);
		} catch (Exception e) {
			System.out.println(e);
		}
		assertNull(q1.poll(), "Queue not null");
		assertNull(q2.poll(), "Queue not null");
	}

	@Test
	void testOneEmptyQueue() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q1.append(q2);
		assertTrue(!q1.isEmpty(), "Wrong result from empty of queue");
		assertEquals(3, q1.size(), "Wrong size of empty queue");
		assertTrue(q2.isEmpty(), "Wrong result from empty of queue");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		for (int i = 1; i <= 3; i++) {
			assertEquals(i, q1.poll(), "poll returns incorrect element");
		}
		assertTrue(q1.isEmpty(), "Wrong result from empty of queue");

		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q2.append(q1);
		assertTrue(!q1.isEmpty(), "Wrong result from empty of queue");
		assertEquals(3, q1.size(), "Wrong size of empty queue");
		assertTrue(q2.isEmpty(), "Wrong result from empty of queue");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		for (int i = 1; i <= 3; i++) {
			assertEquals(i, q1.poll(), "poll returns incorrect element");
		}
		assertTrue(q1.isEmpty(), "Wrong result from empty of queue");
	}

	@Test
	void testAppend() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q2.offer(4);
		q2.offer(5);
		q2.offer(6);
		q1.append(q2);
		for (int i = 1; i <= 6; i++) {
			assertEquals(i, q1.poll(), "poll returns incorrect element");
		}
		System.out.println(q1.size());
		assertTrue(q1.isEmpty(), "Wrong result from empty of queue");
	}

	@Test
	void testSelfAppend() {
		for (int i = 1; i <= 3; i++) {
			q1.offer(i);
		}
		try {
			q1.append(q2);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
