#include <chrono>
#include <thread>
#include "utils.h"
#include <mutex>
#include <iostream>
#include <string.h>

std::mutex m;

namespace utils {
	void sleep(int milli) {
		std::this_thread::sleep_for(std::chrono::milliseconds(milli));
	}

	void busy_work(int milli) {
		sleep(milli);
	}

	int complex_calculation(int milli) {
		sleep(milli);
		return 10;
	}

	void some_initialization(int milli) {
		sleep(milli);
	}

	void free_some_memory(int milli) {
		sleep(milli);
	}

	void print(std::string msg) {
		m.lock();
		std::cout << msg << '\n';
		m.unlock();
	}

	void print(std::string msg, int x) {
		m.lock();
		std::cout << msg << x << '\n';
		m.unlock();
	}
}