#include <chrono>
#include <thread>
#include "utils.h"
#include <mutex>
#include <iostream>
#include <string.h>
#include <map>
#include <iomanip>

std::mutex m;

std::map <std::string, std::chrono::steady_clock::time_point> time_map;

namespace utils {
	void sleep(int milli) {
		std::this_thread::sleep_for(std::chrono::milliseconds(milli));
	}

	void print(std::string msg) {
		m.lock();
		auto now = std::chrono::system_clock::now();
		auto time = std::chrono::system_clock::to_time_t(now);
		std::cout << std::put_time(std::localtime(&time), "%T") << ": " << msg << '\n';
		m.unlock();
	}

	void print(std::string msg, int x) {
		m.lock();
		auto now = std::chrono::system_clock::now();
		auto time = std::chrono::system_clock::to_time_t(now);
		std::cout << std::put_time(std::localtime(&time), "%T")<< ": " << msg << x << '\n';
		m.unlock();
	}

	void print(std::string msg, int x, std::string sfx) {
		m.lock();
		auto now = std::chrono::system_clock::now();
		auto time = std::chrono::system_clock::to_time_t(now);
		std::cout << std::put_time(std::localtime(&time), "%T") << ": " << msg << x << sfx << '\n';
		m.unlock();
	}

	void time_start(std::string name) {
		time_map.insert({ name, std::chrono::high_resolution_clock::now() });
	}

	int time_end(std::string name) {
		auto start = time_map[name];
		time_map.erase(name);
		auto end = std::chrono::high_resolution_clock::now();
		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
		return duration.count();
	}
}