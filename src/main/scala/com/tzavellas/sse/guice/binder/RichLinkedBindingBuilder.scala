/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice
package binder

import com.google.inject._
import com.google.inject.binder.LinkedBindingBuilder
import java.lang.reflect.Constructor;

trait RichLinkedBindingBuilder[T] extends LinkedBindingBuilder[T]
                                     with RichScopedBindingBuilder {

  val builder: LinkedBindingBuilder[T]
  
  def to(implementation: Class[_ <: T]): RichScopedBindingBuilder = {
    builder.to(implementation)
    this
  }
  
  def to(targetKey: Key[_ <: T]): RichScopedBindingBuilder = {
    builder.to(targetKey)
    this
  }
  
  def to(implementation: TypeLiteral[_ <: T]): RichScopedBindingBuilder = {
    builder.to(implementation)
    this
  }
  
  def to[I <:T](implicit i: Manifest[I]): RichScopedBindingBuilder = {
    if (i.typeArguments.isEmpty) {
      builder.to(i.erasure.asInstanceOf[Class[I]])
    } else {
      builder.to(Helpers.typeLiteral(i))
    }
    this
  }
  
  def toInstance(instance: T) {
    builder.toInstance(instance)
  }
  
  def toProvider(implementation: com.google.inject.TypeLiteral[_ <: javax.inject.Provider[_ <: T]]) : RichScopedBindingBuilder = {
    builder.toProvider(implementation)
    this
  }
  
  def toProvider(providerType: Class[_ <: javax.inject.Provider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerType)
    this
  }
  
  def toProvider(providerKey: Key[_ <: javax.inject.Provider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerKey)
    this
  }
  
  def toProvider(provider: Provider[_ <: T]): RichScopedBindingBuilder = {
    builder.toProvider(provider)
    this
  }
  
  def toProvider[P <: Provider[_ <: T]](implicit p: Manifest[P]): RichScopedBindingBuilder = {
    if (p.typeArguments.isEmpty) {
      builder.toProvider(p.erasure.asInstanceOf[Class[P]])
    } else {
      builder.toProvider(Helpers.typeLiteral(p))
    }
    this
  }
  
  def toConstructor[S <: T](constructor: Constructor[S]) : RichScopedBindingBuilder = {
    builder.toConstructor(constructor)
    this
  };
  
  def toConstructor[S <: T](constructor: Constructor[S],implementation: TypeLiteral[_ <: S]) : RichScopedBindingBuilder = {
    builder.toConstructor(constructor,implementation)
    this
  };
  
  def toConstructor[I <:T](constructor: Constructor[I])(implicit i: Manifest[I]): RichScopedBindingBuilder = {
    builder.toConstructor(constructor,Helpers.typeLiteral(i))
    this
  }
}