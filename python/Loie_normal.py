# -*- coding: utf-8 -*-
"""
Created on Wed Jan 16 14:15:15 2019

@author: gabri
"""

import numpy as np 
from scipy.stats import shapiro
from scipy.stats import pearsonr, anderson,chi2_contingency
from scipy.stats import norm, gumbel_l,gumbel_r, expon, logistic
from math import pi
import scipy.stats as stat
import matplotlib.pyplot as plt
import os

def get_DUO(FilePath):
    L=[]
    names1=[]
    names2=[]
    liste_donnee=os.listdir(FilePath)
    for name in liste_donnee :
        liste_donnee.remove(name)
        for name2 in liste_donnee :
            if name2.replace("Duo_","").replace("Duo2_","")==name.replace("Duo2_","").replace("Duo_",""):
                liste_donnee.remove(name2)
                Rendre_lisible(FilePath+"/"+name)
                Rendre_lisible(FilePath+"/"+name2)
                L.append(np.array([np.genfromtxt(FilePath+"/"+name),np.genfromtxt(FilePath+"/"+name2)]))
                if name[:4]=="Duo_":
                    names=name.split("_")
                    names1.append([names[3],names[5]])
                    names2.append([names[4],names[6]])
                else: 
                    names=name.split("_")
                    names1.append([names[4],names[6]])
                    names2.append([names[3],names[5]])
    return L,names1,names2


#à compléter
def get_SOLO(FilePath):
    L=[]
    names1=[]
    liste_donnee=os.listdir(FilePath)
    #print(liste_donnee)
    for name in liste_donnee :
        #liste_donnee.remove(name)
        L.append(np.genfromtxt(FilePath+"/"+name))
        names=name.split("_")
        names1.append([names[2],names[3]])
    print(L)
    return L,names1

def test_Loi(L):
    res=[]
    lois=["gumbel_l","gumbel_r","gumbel","expon","logistic","norm"]
    compteur1=0
    compteur2=0
    for l in L :
        compteur1+=1
        print(l)
        for loi in lois :
            a,b,c=anderson(l,loi)
            print(a)
            print(b)
            if a<b[1]:
                compteur2+=1
                res.append(loi)
                break
        if compteur2<compteur1:
             res.append("loi_inconnu")
             compteur2+=1
    return res     
   
def determination_para(Lois,L):
    Espe=[]
    Variance=[]
    for i in range (len(L)):
        a=0
        b=0
        if Lois[i]=="gumbel_l":
            a,b=gumbel_l.fit(L[i])
            Variance.append((b**2)*(pi**2)/6)
        if Lois[i]=="gumbel_r":
            a,b=gumbel_r.fit(L[i])
            Variance.append((b**2)*(pi**2)/6)
        if Lois[i]=="norm":
            a,b=norm.fit(L[i])
            Variance.appenrd(b**2)
        if Lois[i]=="logistic":
            a,b=logistic.fit(L[i])
            Variance.append((b**2)*(pi**2)/3)
        if Lois[i]=="expo":
            a,b=expon.fit(L[i])
            Variance.append(b**2)
        if Lois[i]=="loi_inconnu":
            Variance.append(0)
        Espe.append(a)
    return Espe, Variance    
    
    

def Coefficient_cor(L):
    Res=[]
    for l in L :
        Res.append(pearsonr(l[0],l[1]))    
    return Res;

def Independance(L) :
    Res=[]
    for l in L :
        print(l[0],l[1])
        Res.append(chi2_contingency(l[0],l[1]))    
    return Res;


def Rendre_lisible(FilePath):
    fichier=open(FilePath,'r')
    lignes=fichier.readlines()
    for L in lignes :
        L.replace('E','e')
        L.replace(",",".")
    fichier.close()

Rendre_lisible("frqM_Duo.txt")
Rendre_lisible("frqM_Duo2.txt")
frqM=np.genfromtxt("frqM_Duo.txt")
frqM2=np.genfromtxt("frqM_Duo2.txt")
#print(frqM)
#frqM_Norm=np.delete(frqM_Norm,0)
#print(frqM2)

#test si les variable obeissent à une loie normal
shapiro(x=frqM)
shapiro(x=frqM2)



def afficher_D(Vecteur):
    Max=np.max(Vecteur)
    Min=np.min(Vecteur)
    Size=np.size(Vecteur)
    Nb_subdiv=int(Size/3)
    pas=(Max-Min)/Nb_subdiv
    D=np.zeros(Nb_subdiv)
    for v in Vecteur:
        if (v==Max):
            D[int((v-Min-0.000001)/pas)]+=1
        else :    
            D[int((v-Min)/pas)]+=1
    X=np.linspace(Min+pas/2,Max-pas/2,Nb_subdiv)
    plt.plot(X,D)
    print(D)
    
def Cov(L1,L2):
    E1=np.mean(L1)
    E2=np.mean(L2)
    n=np.size(L1)
    return ((np.sum((L1-E1)*(L2-E2)))/(n-1))/(E1*E2)

if __name__=="__main__":
    #L_Duo,nom1,nom2=get_DUO("DUO")
    #Corelation=Coefficient_cor(L_Duo)
    #Indep=Independance(L_Duo)
    L,noms=get_SOLO("SOLO")
    LOIS=test_Loi(L)
    E,V=determination_para(LOIS,L)
    
    