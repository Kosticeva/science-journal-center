package com.uns.ftn.sciencejournal.service.utils;

public class BoolQueryTokenParser {

    public static BoolOperator getLastOperator(String query) {
        int indexOfAnd = query.lastIndexOf(BoolOperator.Operator.AND.getExpression());
        int indexOfOr = query.lastIndexOf(BoolOperator.Operator.OR.getExpression());

        if (indexOfAnd == indexOfOr) {
            return null;
        } else if (indexOfAnd > indexOfOr) {
            return new BoolOperator(BoolOperator.Operator.AND, indexOfAnd);
        } else {
            return new BoolOperator(BoolOperator.Operator.OR, indexOfOr);
        }
    }

    public static BoolOperator getDifferentLastOperator(String query, BoolOperator lastOperator) {
        BoolOperator newLastOperator = lastOperator;
        while(lastOperator.equals(newLastOperator)) {
            query = getTokenBeforeOperator(query, newLastOperator);
            lastOperator = newLastOperator;
            newLastOperator = getLastOperator(query);

            if(newLastOperator == null) {
                return null;
            }
        }

        return newLastOperator;
    }

    public static String getTokenAfterOperator(String query, BoolOperator operator) {
        return query.substring(operator.getIndex() + 4);
    }

    public static String getTokenBeforeOperator(String query, BoolOperator operator) {
        return query.substring(0, operator.getIndex());
    }

    public static String getTokenParameter(String token) {
        return token.substring(0, token.indexOf("="));
    }

    public static String getTokenValue(String token) {
        return token.substring(token.indexOf("=") + 1);
    }

    public static boolean checkIfTokenValueIsPhrase(String value) {
        return value.trim().startsWith("\"") && value.trim().endsWith("\"");
    }

    public static String removeQuotationMarksFromPhrase(String phrase) {
        String trimmedPhrase = phrase.trim();
        return trimmedPhrase.substring(1, trimmedPhrase.length() - 1);
    }

    public static String convertSerbianParameterNameToEsIndexName(String parameter) {
        if (prepareParameter(parameter).equals("naslov") || prepareParameter(parameter).equals("rad")) {
            return "title";
        }

        if (prepareParameter(parameter).equals("title")) {
            return prepareParameter(parameter);
        }

        if (prepareParameter(parameter).equals("casopis") || prepareParameter(parameter).equals("časopis") || prepareParameter(parameter).equals("magazin")) {
            return "magazine.title";
        }

        if (prepareParameter(parameter).startsWith("magazine")) {
            if (prepareParameter(parameter).endsWith("title")) {
                if (prepareParameter(parameter).length() == 14) {
                    return "magazine.title";
                }
            }
        }

        if (prepareParameter(parameter).equals("sadrzaj") || prepareParameter(parameter).equals("sadržaj")) {
            return "content";
        }

        if (prepareParameter(parameter).equals("content")) {
            return prepareParameter(parameter);
        }

        if (prepareParameter(parameter).startsWith("kljucne") || prepareParameter(parameter).startsWith("ključne")) {
            if (prepareParameter(parameter).endsWith("reci") || prepareParameter(parameter).endsWith("reči")) {
                if (prepareParameter(parameter).length() == 12) {
                    return "keywords";
                }
            }
        }

        if (prepareParameter(parameter).equals("keywords")) {
            return prepareParameter(parameter);
        }

        if (prepareParameter(parameter).startsWith("naucna") || prepareParameter(parameter).startsWith("naučna")) {
            if (prepareParameter(parameter).endsWith("oblast")) {
                if (prepareParameter(parameter).length() == 13) {
                    return "field";
                }
            }
        }

        if (prepareParameter(parameter).equals("oblast")) {
            return "field";
        }

        if (prepareParameter(parameter).equals("field")) {
            return prepareParameter(parameter);
        }

        if (prepareParameter(parameter).startsWith("science")) {
            if (prepareParameter(parameter).endsWith("field")) {
                if (prepareParameter(parameter).length() == 13) {
                    return "field";
                }
            }
        }

        if (prepareParameter(parameter).startsWith("autor")) {
            if (prepareParameter(parameter).endsWith("ime")) {
                if (prepareParameter(parameter).length() == 9) {
                    return "authors.firstName";
                }
            }

            if (prepareParameter(parameter).endsWith("prezime")) {
                if (prepareParameter(parameter).length() == 13) {
                    return "authors.lastName";
                }
            }
        }

        if (prepareParameter(parameter).startsWith("author")) {
            if (prepareParameter(parameter).endsWith("firstname")) {
                if (prepareParameter(parameter).length() == 16) {
                    return "authors.firstName";
                }
            }

            if (prepareParameter(parameter).endsWith("lastname")) {
                if (prepareParameter(parameter).length() == 15) {
                    return "authors.lastName";
                }
            }
        }

        if (prepareParameter(parameter).equals("ime")) {
            return "authors.firstName";
        }

        if (prepareParameter(parameter).equals("prezime")) {
            return "authors.lastName";
        }

        if (prepareParameter(parameter).endsWith("name")) {
            if (prepareParameter(parameter).startsWith("first")) {
                if (prepareParameter(parameter).length() == 10 || prepareParameter(parameter).length() == 9) {
                    return "authors.firstName";
                }
            }

            if (prepareParameter(parameter).startsWith("last")) {
                if (prepareParameter(parameter).length() == 9 || prepareParameter(parameter).length() == 8) {
                    return "authors.lastName";
                }
            }
        }

        return null;
    }

    private static String prepareParameter(String parameter) {
        return parameter.toLowerCase().trim();
    }
}
