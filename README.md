# Mapper

## Description
Simple mapper based on java reflections. The advantage of this implementation is that project is small and easy
configurable. This project could help you avoid boilerplate code. Most popular use case is mapping DTO objects.

## How it works
```
### Step 1: Mapper
---------------------------------------------------------
|                                                       |
|    +------------------------+                         |
|    |                        |                         |
|    |  Adapter for           |                         |
|    |  MappingManager        |                         |
|    |                        |                         |
|    |  Gives convenient      |                         |
|    |  methods for mapping   |                         |
|    |                        |                         |
|    +------------------------+                         |
|                                                       |
---------------------------------------------------------

               |
               v

### Step 2: MappingManager
---------------------------------------------------------
|                                                       |
|    +------------------------+                         |
|    |                        |                         |
|    |  Choose Provider       |                         |
|    |  - Custom Provider     |                         |
|    |  - Basic Mapper        |                         |
|    |                        |                         |
|    |  +-------------------+ |                         |
|    |  | CustomProvider1   | |                         |
|    |  +-------------------+ |                         |
|    |  | CustomProvider2   | |                         |
|    |  +-------------------+ |                         |
|    |  | ...               | |                         |
|    |  +-------------------+ |                         |
|    |  | Basic Mapper      | |                         |
|    |  +-------------------+ |                         |
|    |                        |                         |
|    +------------------------+                         |
|                                                       |
---------------------------------------------------------
                                                
                                            /             \
                                           v               v

### Step 3: MappingProvider
----------------------------------------            ----------------------------------------
|                                      |            |                                      |
|    +------------------------+        |            |    +------------------------+        |
|    |                        |        |            |    |                        |        |
|    |  Your Custom Provider  |        |            |    |  Basic Mapper          |        |
|    |                        |        |            |    |                        |        |
|    +------------------------+        |            |    +------------------------+        |
|                                      |            |                                      |
----------------------------------------            ----------------------------------------



```

## How works Basic Mapper
```
Step 1: MappingSchemaResolver
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

Step 2: MappingSchemaProcessor
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

## Getting started
- [mapper-starter-spring](./mapper-starter-spring/README.md)
- [mapper-core](./mapper-core/README.md)

## Collaborator
temez: https://github.com/temez