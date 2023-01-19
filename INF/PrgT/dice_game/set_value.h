#pragma once
#include "utils.h"
#include <future>

namespace SetValue {
	void producer_thread(std::promise<int>& promise);
	void consumer_thread(std::future<int>& future);
}

