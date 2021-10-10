FROM jupyter/tensorflow-notebook:latest

MAINTAINER M. Breunig <markus.breunig@th-rosenheim.de>

USER root

RUN pip install PyNLPl
RUN conda install --yes --name root spacy
RUN conda install --yes --name root gensim
RUN conda install --yes --name root nltk
