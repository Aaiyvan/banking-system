package dev.aaiyvan.qrservice.exception;

import lombok.NoArgsConstructor;

/**
 * Exception thrown if a QR code cannot be generated for a given link.
 */
@NoArgsConstructor
public class QRGenerationException extends RuntimeException {

    /**
     * Constructs a new QRGenerationException with the specified link and cause.
     *
     * @param link the link for which the QR code cannot be generated
     * @param tr   the cause of the exception
     */
    public QRGenerationException(
            final String link,
            final Throwable tr) {
        super("QR with link: %s cannot be generated, cause: %s".formatted(link, tr.getMessage()));
    }

}
