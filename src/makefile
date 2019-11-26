JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:  ;  $(JC) $(JFLAGS) $*.java

CLASSES =   building.java \
        min_heap.java \
        RBTree.java \
        RBTreeNode.java \
        risingCity.java

default: classes

classes: $(CLASSES:.java=.class)

clean: ; $(RM) *.class