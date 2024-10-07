#ifndef IMPLEMENTATION_H
#define IMPLEMENTATION_H

#include <type_traits>

template <typename Derived, typename Base>
concept Implementation = std::is_abstract_v<Base> && !std::is_abstract_v<Derived> && std::is_base_of_v<Base, Derived>;

#endif //IMPLEMENTATION_H
