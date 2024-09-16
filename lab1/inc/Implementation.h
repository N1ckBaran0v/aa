#ifndef __IMPLEMENTATION_H__
#define __IMPLEMENTATION_H__

#include <concepts>

template <typename Derived, typename Base>
concept Implementation = std::is_abstract_v<Base> && !std::is_abstract_v<Derived> && std::is_base_of_v<Base, Derived>;

#endif //__IMPLEMENTATION_H__