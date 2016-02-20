# Energy Services Interface

The Energy Services Interface is a free, open source Java-based software package (Apache 2.0 license) that allows anyone
building an interface to a Distributed Energy Resource (DER) to participate in an energy market.  This specific 
implementation is designed for a TEMiX-style market, but the framework should be extensible to support other 
market models.

## How does it work?
Simply implement the Agent interface and write a main method to operate the Market Participant class on the time basis you 
choose.  In order to participate in a market, you'll need to be able to forecast your energy needs,
schedule your resource, and report the energy used or generated.  It's that easy.

## Dependencies
An additional software package is required to provide the technical Virtual End Node (VEN) functionality to interface with the
market facilitator, or Virtual Top Node (VTN).  This link is coming soon.



