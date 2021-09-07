//
// Created by open on 2021-09-07.
//

#include <iostream>
using namespace std;

class Han {
    private :
        int a;
        int b;
    public :
        void print() {
            printf("%d", &a);
            std :: cout << "Hi";
        }
}