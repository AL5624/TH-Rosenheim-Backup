namespace classic_threads {
    void thread_calc(int& value) {
        value = calc_val();
    }

    void run() {
        int value;

        std::thread th2(thread_calc, value);
        busy_work(50);
        th2.join();

        std::cout << "value: " << value << '\n';
    }
}