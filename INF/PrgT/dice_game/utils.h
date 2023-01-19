#pragma once
#include <string>

namespace utils {
	void sleep(int);

	void busy_work(int milli = 1000);

	int complex_calculation(int milli = 1000);

	void some_initialization(int milli = 1000);

	void free_some_memory(int milli = 1000);

	void print(std::string msg);

	void print(std::string msg, int x);

	void print(std::string msg, int x, std::string sfx);

	void time_start(std::string name);

	int time_end(std::string name);
}