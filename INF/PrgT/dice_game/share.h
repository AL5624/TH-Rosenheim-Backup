#pragma once
#include <future>

namespace Share {
	void producer_thread(std::promise<int>&);
	void consumer_thread_1(std::shared_future<int>);
	void consumer_thread_2(std::shared_future<int>);
}