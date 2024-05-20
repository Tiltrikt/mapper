# Mapper

## Description
Simple mapper based on java reflections. The advantage of this implementation is that project is small and easy
configurable. This project could help you avoid boilerplate code. Most popular use case is mapping DTO objects.

## How it works
```
Step 1: ObjectFactory
---------------------------------------------------------
|                                                       |
|    +------------------------+                         |
|    |                        |                         |
|    |  New Instance          |                         |
|    |  of Target Class       |                         |
|    |  (TargetObject)        |                         |
|    |                        |                         |
|    +------------------------+                         |
|                                                       |
---------------------------------------------------------

               |
               v

Step 2: MappingSchemaResolver
---------------------------------------------------------
|                                                       |
|    +------------------------+                         |
|    |                        |                         |
|    |  MappingSchema         |                         |
|    |  Map<Field, Field>     |                         |
|    |                        |                         |
|    |  +-------------------+ |                         |
|    |  | sourceField1 ->   | |                         |
|    |  | targetField1      | |                         |
|    |  +-------------------+ |                         |
|    |  | sourceField2 ->   | |                         |
|    |  | targetField2      | |                         |
|    |  +-------------------+ |                         |
|    |  | ...               | |                         |
|    |  +-------------------+ |                         |
|    |                        |                         |
|    +------------------------+                         |
|                                                       |
---------------------------------------------------------

               |
               v

Step 3: MappingSchemaProcessor
---------------------------------------------------------
|                                                       |
|    +------------------------+                         |
|    |                        |                         |
|    |  SourceObject          |                         |
|    |  +-------------------+ |                         |
|    |  | sourceField1      | |                         |
|    |  +-------------------+ |                         |
|    |  | sourceField2      | |                         |
|    |  +-------------------+ |                         |
|    |  | ...               | |                         |
|    |  +-------------------+ |                         |
|    |                        |                         |
|    +-----------|------------+                         |
|                |                                      |
|                v                                      |
|    +-----------|------------+                         |
|    |                        |                         |
|    |  TargetObject          |                         |
|    |  +-------------------+ |                         |
|    |  | targetField1      | |                         |
|    |  +-------------------+ |                         |
|    |  | targetField2      | |                         |
|    |  +-------------------+ |                         |
|    |  | ...               | |                         |
|    |  +-------------------+ |                         |
|    |                        |                         |
|    +------------------------+                         |
|                                                       |
---------------------------------------------------------
|                                                       |
|  Mapping fields from SourceObject to TargetObject     |
|  using MappingSchema:                                 |
|  - sourceField1 value -> targetField1                 |
|  - sourceField2 value -> targetField2                 |
|  - ...                                                |
|                                                       |
---------------------------------------------------------

```

<em>Note: some steps might be omitted depends on the method of **Mapper** you choose</em>

## Getting started
- [mapper-starter-spring](./mapper-starter-spring/README.md)
- [mapper-core](./mapper-core/README.md)

## Collaborator
temez: https://github.com/temez