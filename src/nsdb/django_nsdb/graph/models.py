from django.db import models

# Basic information
class Owner(models.Model):
    name = models.CharField(max_length=256)

class Xref(models.Model):
    name = models.CharField(max_length=256)
    link = models.CharField(max_length=256)
    
class Type(models.Model):
    name = models.CharField(max_length=256)
    description = models.TextField()

class Descriptor(models.Model):
    name
    description




class Attribute(models.Model):
    content = models.CharField(max_length=256)
    type = models.ForeignKey(Type)
    
# Graph information
class Node(models.Model):
    type = models.ForeignKey(Type)
    xref = models.ForeignKey(Xref)
    attributes = models.ForeignKey(Attribute)

class Edge(models.Model):
    type = models.ForeignKey(Type)
    xref = models.ForeignKey(Xref)
    source = models.ForeignKey(Node)
    target = models.ForeignKey(Target)
    attributes = models.ManyToManyRel(Attribute)
        
class Graph(models.Model):
    type = models.ForeignKey(Type)
    xref = models.ForeignKey(XRef)
    edges = models.ManyToManyRel(Edge)
    nodes = models.ManyToManyRel(Node)

# A mapping is a mapping of an attribute on the nodes or the edges
class Mapping(models.Model):
    name = models.CharField(max_length=256)
    descriptors = models.ManyToManyRel(Descriptor)

class NodeMapping(Mapping):
    pass

class EdgeMapping(Mapping):
    pass

class GraphMapping(Mapping):
    pass

class EdgeAttribute(models.Model):    
    edge = models.ForeignKey(Edge)
    attribute = models.ForeignKey(Attribute)
    mapping = models.ForeignKey(Mapping)
    
class NodeAttribute(models.Model):
    node = models.ForeignKey(Node)
    attribute = models.ForeignKey(Attribute)
    mapping = models.ForeignKey(Mapping)
    
class GraphAttribute(models.Model):
    graph = models.ForeignKey(Graph)
    attribute = models.ForeignKey(Attribute)
    mapping = models.ForeignKey(Mapping)
    

class Collection(models.Model):
    xref = models.ForeignKey(Xref)
    owner = models.ForeignKey(Owner)
    descriptors = models.ManyToManyRel(Descriptor)
    mappings = models.ManyToManyRel(Collection)