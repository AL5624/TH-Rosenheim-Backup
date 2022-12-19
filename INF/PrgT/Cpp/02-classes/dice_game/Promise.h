#pragma once

#include <iostream>
#include <future>
#include "Future.h"
#include "SharedState.h"
#include <mutex>

template <class T>
class Promise {
private:

	SharedState<T> shared_state;
	bool promise_already_satisfied = false;

	std::mutex set_content_mutex;

public:

	Promise() {};

	// no copy
	Promise(const Promise&) = delete;

	// move
	Promise(Promise&& x) noexcept {};

	~Promise() {};

	Future<T> get_future() {
		if (!this->shared_state.is_valid()) {
			throw std::future_errc::no_state;
		}

		return Future<T>(this->shared_state);
	};

	void set_value(const T &value) {
		set_content_mutex.lock();

		if (this->shared_state.get_state() == no_state) {
			throw std::future_errc::no_state;
		}

		if (this->shared_state.get_state() != empty) {
			throw std::future_errc::promise_already_satisfied;
		}

		this->shared_state.set_value(value);

		this->shared_state.set_state(ready);

		set_content_mutex.unlock();
	};

	void set_exception(const std::exception &ex) {
		set_content_mutex.lock();

		if (this->shared_state.get_state() == no_state) {
			throw std::future_errc::no_state;
		}

		if (this->shared_state.get_state() != empty) {
			throw std::future_errc::promise_already_satisfied;
		}

		this->shared_state.set_exception(ex);

		this->shared_state.set_state(ready);

		set_content_mutex.unlock();
	};

	void set_value_at_thread_exit(const T &value) {
		set_content_mutex.lock();

		if (this->shared_state.get_state() == no_state) {
			throw std::future_errc::no_state;
		}

		if (this->shared_state.get_state() != empty) {
			throw std::future_errc::promise_already_satisfied;
		}

		this->shared_state.set_value(value);

		this->shared_state.set_state(valid);

		set_content_mutex.unlock();
	};

	void set_exception_at_thread_exit(const std::exception &ex) {
		set_content_mutex.lock();

		if (this->shared_state.get_state() == no_state) {
			throw std::future_errc::no_state;
		}

		if (this->shared_state.get_state() != empty) {
			throw std::future_errc::promise_already_satisfied;
		}

		this->shared_state.set_exception(ex);

		this->shared_state.set_state(valid);

		set_content_mutex.unlock();
	};

	void swap(Promise &x) noexcept {
		this->shared_state = x.shared_state;
	};

	// template <class T> static void swap(Promise<T>&, Promise<T>&) noexcept;
};